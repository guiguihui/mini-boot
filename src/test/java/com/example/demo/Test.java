package com.example.demo;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {

        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String CarId = "";
        String DateTime = "";

        //声明Connection对象
        Connection con;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mini-boot?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String password = "admin";


        /**
         * 从文件中读取车牌与时间
         */
        String fileName = "D:/毕业设计/车牌识别代码/Data.txt";
        fileName = new String (fileName.getBytes("gbk"), "utf-8");
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lastLine = "";
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                /*读取文件最后一行*/
                String currentLine = "";
                while ((currentLine = bufferedReader.readLine()) != null) {
                    lastLine = currentLine;
                }
            } catch (Exception e) {
                System.out.println("file read error");
            }
            /*分段读取车牌与时间*/
            CarId = lastLine.substring(0,7);
            DateTime = lastLine.substring(8,27);

            System.out.println(CarId);
            System.out.println(DateTime);


            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }


        /**
         * 写入数据库
         */
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed()){
                System.out.println("Succeeded connecting to the Database!");
            }
            Statement statement = con.createStatement();


            String sqlForParkingFee = "SELECT * FROM parking where Parking_id=1";
            ResultSet parking = statement.executeQuery(sqlForParkingFee);
            float ParkingFee = 0;  //用来获取该停车场停车收费标准的变量
            int availableSpace =0;

            if(parking.next()){
                ParkingFee = parking.getFloat("Parking_fee");
                availableSpace = parking.getInt("Parking_available");
            }

            String sql1 = "SELECT * FROM reserve where Car_id='"+ CarId +"' order by Reserve_id DESC limit 1";
            ResultSet reserve = statement.executeQuery(sql1);


            /*先看是否进行了预定*/
            if(reserve.next()){
                Date ReserveEnd = reserve.getDate("Reserve_end");
                Date ReserveStart = reserve.getDate("Reserve_start");
                String ReserveId = reserve.getString("Reserve_id");
                int parkingId = reserve.getInt("Parking_id");
                int spaceId = reserve.getInt("Space_id");
                /*如果进行了预定*/
                if (ReserveEnd==null){
                    /*计算预定费用*/
                    long startTime = ReserveStart.getTime();
                    Date tempDate = simpleFormat.parse(DateTime);
                    long endTime = tempDate.getTime();
                    int hours = (int) ((endTime - startTime) / (1000*60*60));
                    float ReserveFee = ParkingFee*hours; //用来计算预定花了多少钱
                    /*完成预定*/
                    String sqlForReserve="UPDATE reserve SET Reserve_end='"+ DateTime + "',Reserve_fee="+ ReserveFee +" WHERE Car_id='"+ CarId +"' order by Reserve_id DESC limit 1";
                    statement.execute(sqlForReserve);
                    /*新建账单*/
                    String sql4="INSERT INTO bill(Bill_id,Reserve_id,Parking_id,Space_id,Car_id,Bill_start,Reserve_fee) values(?,?,?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql4);
                    /*账单的id等于开始时间+用户id+停车位id*/
                    String billStart = DateTime.substring(0,4)  + DateTime.substring(5,7) + DateTime.substring(8,10) + DateTime.substring(11,13) + DateTime.substring(14,16) + DateTime.substring(17,19);
                    String billId = billStart + ReserveId.substring(14,18);
                    pst.setString(1, billId);
                    pst.setString(2, ReserveId);
                    pst.setInt(3,parkingId);
                    pst.setInt(4,spaceId);
                    pst.setString(5,CarId);
                    pst.setString(6,DateTime);
                    pst.setFloat(7,ReserveFee);
                    pst.execute();
                    System.out.println("complete reserve and new bill");
                }else {
                    /*没有查到正在进行的预定 但是有正在进行的订单 此时就是车辆出库*/
                    String recentBill = "SELECT * FROM bill where Car_id='"+ CarId +"' order by Bill_id DESC limit 1";
                    ResultSet bill = statement.executeQuery(recentBill);
                    if(bill.next()){
                        Date BillStart = bill.getDate("Bill_start");
                        Date BillEnd = bill.getDate("Bill_end");
                        float ReserveFee = bill.getFloat("Reserve_fee");
                        String BillId = bill.getString("Bill_id");

                        long startTime = BillStart.getTime();
                        Date tempDate = simpleFormat.parse(DateTime);
                        long endTime = tempDate.getTime();
                        int hours = (int) ((endTime - startTime) / (1000*60*60));
                        float BillFee = ParkingFee*hours + ReserveFee; //用来计算本次停车费用

                        if(BillEnd==null){
                            String sqlForBill="UPDATE bill SET Bill_end='"+ DateTime + "',Bill_fee="+ BillFee +" WHERE Car_id='"+ CarId +"' order by Bill_id DESC limit 1";
                            statement.execute(sqlForBill);
                            String state = "空闲";
                            state = new String (state.getBytes("gbk"), "utf-8");
                            String sqlForSpace="UPDATE space SET Space_state='"+ state +"',Car_id=\"\" WHERE Car_id='"+ CarId +"' limit 1";
                            statement.execute(sqlForSpace);
                            availableSpace++;
                            String sqlForParking="UPDATE parking SET Parking_available="+ availableSpace +" where Parking_id=1";
                            statement.execute(sqlForParking);
                            System.out.println("complete bill");
                        }else {
                            /*没有正在进行中的账单(没有进行预定直接到停车场)*/
                            String newBill="INSERT INTO bill(Bill_id,Parking_id,Space_id,Car_id,Bill_start,Reserve_fee) values(?,?,?,?,?,?)";
                            PreparedStatement pst = con.prepareStatement(newBill);
                            /*账单的id等于开始时间+用户id+停车位id*/
                            String billStart = DateTime.substring(0,4)  + DateTime.substring(5,7) + DateTime.substring(8,10) + DateTime.substring(11,13) + DateTime.substring(14,16) + DateTime.substring(17,19);
                            String billId = billStart + "3101";
                            pst.setString(1, billId);
                            pst.setInt(2, 1);
                            pst.setInt(3,101);
                            pst.setString(4,CarId);
                            pst.setString(5,DateTime);
                            pst.setFloat(6,0);
                            pst.execute();
                            availableSpace--;
                            String sqlForParking="UPDATE parking SET Parking_available="+ availableSpace +" where Parking_id=1";
                            statement.execute(sqlForParking);
                            System.out.println("new bill");
                        }
                    }
                }
            }
            reserve.close();
            con.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
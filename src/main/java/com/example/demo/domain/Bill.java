package com.example.demo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import java.util.Date;

@Data
public class Bill {
    @TableId(value = "Bill_id",type = IdType.INPUT)
    private String BillId;
    private String ReserveId;
    private int ParkingId;
    private int SpaceId;
    private String CarId;
    private Date BillStart;
    private Date BillEnd;
    private float ReserveFee;
    private float BillFee;
    //逻辑删除 0表未删除 1表已删除
    @TableLogic(value = "0",delval = "1")
    private Integer IsDeleted;

    @Override
    public String toString() {
        return "Bill{" +
                "BillId='" + BillId + '\'' +
                ", ReserveId='" + ReserveId + '\'' +
                ", ParkingId=" + ParkingId +
                ", SpaceId=" + SpaceId +
                ", CarId='" + CarId + '\'' +
                ", BillStart=" + BillStart +
                ", BillEnd=" + BillEnd +
                ", ReserveFee=" + ReserveFee +
                ", BillFee=" + BillFee +
                ", IsDeleted=" + IsDeleted +
                '}';
    }

    public Integer getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        IsDeleted = isDeleted;
    }

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public String getReserveId() {
        return ReserveId;
    }

    public void setReserveId(String reserveId) {
        ReserveId = reserveId;
    }

    public int getParkingId() {
        return ParkingId;
    }

    public void setParkingId(int parkingId) {
        ParkingId = parkingId;
    }

    public int getSpaceId() {
        return SpaceId;
    }

    public void setSpaceId(int spaceId) {
        SpaceId = spaceId;
    }

    public String getCarId() {
        return CarId;
    }

    public void setCarId(String carId) {
        CarId = carId;
    }

    public Date getBillStart() {
        return BillStart;
    }

    public void setBillStart(Date billStart) {
        BillStart = billStart;
    }

    public Date getBillEnd() {
        return BillEnd;
    }

    public void setBillEnd(Date billEnd) {
        BillEnd = billEnd;
    }

    public float getReserveFee() {
        return ReserveFee;
    }

    public void setReserveFee(float reserveFee) {
        ReserveFee = reserveFee;
    }

    public float getBillFee() {
        return BillFee;
    }

    public void setBillFee(float billFee) {
        BillFee = billFee;
    }
}

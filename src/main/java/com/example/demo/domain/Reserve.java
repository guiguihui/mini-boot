package com.example.demo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class Reserve {
    @TableId(type = IdType.INPUT)
    private String ReserveId;
    private int ParkingId;
    private int SpaceId;
    private String CarId;
    private Date ReserveStart;
    private Date ReserveEnd;
    private float ReserveFee;
    //逻辑删除 0表未删除 1表已删除
    @TableLogic(value = "0",delval = "1")
    private Integer IsDeleted;

    @Override
    public String toString() {
        return "Reserve{" +
                "ReserveId='" + ReserveId + '\'' +
                ", ParkingId=" + ParkingId +
                ", SpaceId=" + SpaceId +
                ", CarId='" + CarId + '\'' +
                ", ReserveStart=" + ReserveStart +
                ", ReserveEnd=" + ReserveEnd +
                ", ReserveFee=" + ReserveFee +
                ", IsDeleted=" + IsDeleted +
                '}';
    }

    public Integer getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        IsDeleted = isDeleted;
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

    public Date getReserveStart() {
        return ReserveStart;
    }

    public void setReserveStart(Date reserveStart) {
        ReserveStart = reserveStart;
    }

    public Date getReserveEnd() {
        return ReserveEnd;
    }

    public void setReserveEnd(Date reserveEnd) {
        ReserveEnd = reserveEnd;
    }

    public float getReserveFee() {
        return ReserveFee;
    }

    public void setReserveFee(float reserveFee) {
        ReserveFee = reserveFee;
    }
}

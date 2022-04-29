package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;


@Data
public class Parking {
    @TableId
    private int ParkingId;
    private String ParkingName;
    private String ParkingAddress;
    private int ParkingSpace;
    private int ParkingAvailable;
    private float ParkingLatitude;
    private float ParkingLongitude;
    private float ParkingFee;
    //逻辑删除 0表未删除 1表已删除
    @TableLogic(value = "0",delval = "1")
    private Integer IsDeleted;

    @Override
    public String toString() {
        return "Parking{" +
                "ParkingId=" + ParkingId +
                ", ParkingName='" + ParkingName + '\'' +
                ", ParkingAddress='" + ParkingAddress + '\'' +
                ", ParkingSpace=" + ParkingSpace +
                ", ParkingAvailable=" + ParkingAvailable +
                ", ParkingLatitude=" + ParkingLatitude +
                ", ParkingLongitude=" + ParkingLongitude +
                ", ParkingFee=" + ParkingFee +
                ", IsDeleted=" + IsDeleted +
                '}';
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        IsDeleted = isDeleted;
    }

    public int getParkingId() {
        return ParkingId;
    }

    public void setParkingId(int parkingId) {
        ParkingId = parkingId;
    }

    public String getParkingName() {
        return ParkingName;
    }

    public void setParkingName(String parkingName) {
        ParkingName = parkingName;
    }

    public String getParkingAddress() {
        return ParkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        ParkingAddress = parkingAddress;
    }

    public int getParkingSpace() {
        return ParkingSpace;
    }

    public void setParkingSpace(int parkingSpace) {
        ParkingSpace = parkingSpace;
    }

    public int getParkingAvailable() {
        return ParkingAvailable;
    }

    public void setParkingAvailable(int parkingAvailable) {
        ParkingAvailable = parkingAvailable;
    }

    public float getParkingLatitude() {
        return ParkingLatitude;
    }

    public void setParkingLatitude(float parkingLatitude) {
        ParkingLatitude = parkingLatitude;
    }

    public float getParkingLongitude() {
        return ParkingLongitude;
    }

    public void setParkingLongitude(float parkingLongitude) {
        ParkingLongitude = parkingLongitude;
    }

    public float getParkingFee() {
        return ParkingFee;
    }

    public void setParkingFee(float parkingFee) {
        ParkingFee = parkingFee;
    }
}

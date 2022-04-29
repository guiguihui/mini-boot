package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Space {
    @TableId(type = IdType.INPUT)
    private Integer SpaceId;
    private Integer ParkingId;
    private String SpaceState;
    private String CarId;
    //逻辑删除 0表未删除 1表已删除
    @TableLogic(value = "0",delval = "1")
    private Integer IsDeleted;

    @Override
    public String toString() {
        return "Space{" +
                "SpaceId=" + SpaceId +
                ", ParkingId=" + ParkingId +
                ", SpaceState='" + SpaceState + '\'' +
                ", CarId='" + CarId + '\'' +
                ", IsDeleted=" + IsDeleted +
                '}';
    }

    public Integer getSpaceId() {
        return SpaceId;
    }

    public void setSpaceId(Integer spaceId) {
        SpaceId = spaceId;
    }

    public Integer getParkingId() {
        return ParkingId;
    }

    public void setParkingId(Integer parkingId) {
        ParkingId = parkingId;
    }

    public String getSpaceState() {
        return SpaceState;
    }

    public void setSpaceState(String spaceState) {
        SpaceState = spaceState;
    }

    public String getCarId() {
        return CarId;
    }

    public void setCarId(String carId) {
        CarId = carId;
    }

    public Integer getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        IsDeleted = isDeleted;
    }
}

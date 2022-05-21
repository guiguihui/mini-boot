package com.example.demo.controller.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class BillDTO {
    private String date;
    private int billCount;
    private double billSum;




    @Override
    public int hashCode() {
        return Objects.hash(date, billCount, billSum);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBillCount() {
        return billCount;
    }

    public void setBillCount(int billCount) {
        this.billCount = billCount;
    }

    public double getBillSum() {
        return billSum;
    }

    public void setBillSum(float billSum) {
        this.billSum = billSum;
    }
}

package com.example.demo.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class date {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(new Date());

        Date d = c.getTime();
        String day = format.format(d);
        System.out.println(day);
    }


}

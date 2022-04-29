package com.example.demo.dao;

import com.example.demo.domain.Reserve;
import com.example.demo.domain.Space;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
public class ReserveDaoTestCase {
    @Autowired
    private ReserveDao reserveDao;

    @Test
    void  testGetAll(){
        System.out.println(reserveDao.selectList(null));
    }

    @Test
    void testSave(){
        Reserve reserve = new Reserve();
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        System.out.println(timeStamp);

        reserve.setReserveId("100014202204211928");
        reserve.setParkingId(1);
        reserve.setSpaceId(10002);
        reserve.setCarId("’„A98554");
        reserve.setReserveStart(timeStamp);
        reserve.setReserveEnd(timeStamp);
        reserve.setReserveFee(56.12f);
        reserveDao.insert(reserve);
    }

    @Test
    void  testTime(){
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        System.out.println(timeStamp);
    }
}



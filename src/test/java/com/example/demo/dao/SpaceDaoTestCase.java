package com.example.demo.dao;

import com.example.demo.domain.Space;
import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpaceDaoTestCase {
    @Autowired
    private SpaceDao spaceDao;

    @Test
    void  testGetAll(){
        System.out.println(spaceDao.selectList(null));
    }

    @Test
    void testSave(){
        Space space = new Space();
        space.setSpaceId(103);
        space.setParkingId(1);
        space.setCarId("’„B78954");
        space.setSpaceState("’º”√");
        spaceDao.insert(space);
    }

}

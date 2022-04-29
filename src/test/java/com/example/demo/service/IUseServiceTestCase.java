package com.example.demo.service;

import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class IUseServiceTestCase {

    @Autowired
    private IUserService iUserService;

    @Test

    void TestSave(){
        User user = new User();
        user.setUserPassword("123456");
        user.setCarId("’„F45845");
        user.setUserGender(1);
        user.setUserName("zhh");
        user.setUserPhone("15647343897");
        iUserService.save(user);
    }
}

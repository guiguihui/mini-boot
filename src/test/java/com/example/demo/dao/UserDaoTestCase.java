package com.example.demo.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.domain.User;
import com.sun.javaws.IconUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTestCase {

    @Autowired
    private UserDao userDao;

    @Test
    void  testGetAll(){
        System.out.println(userDao.selectList(null));
    }


    @Test
    void testSave(){
        User user = new User();
        user.setUserName("zhh");
        user.setCarId("’„A78554");
        user.setUserGender(1);
        user.setUserPassword("4567dfa8");
        user.setUserPhone("1582206445");
        //System.out.println(user.toString());
        userDao.insert(user);
    }

    @Test
    void testGetPage(){
        IPage page = new Page(1,5);
        userDao.selectPage(page, null);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }
}

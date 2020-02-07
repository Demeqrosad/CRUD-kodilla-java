package com.kodilla.crud.tasks.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminConfigTest
{
    @Autowired
    private AdminConfig adminConfig;

    @Test
    public void getAdminMail()
    {
        //Given

        //When
        String adminMail = adminConfig.getAdminMail();
        //Then
        Assert.assertEquals("demetriusz@kurosad.pl", adminMail);
    }
}
package com.computer_database.service;

import org.junit.Assert;
import org.junit.Test;

public class TestBidon {

    @Test
    public void test() {
        int iGet = ComputerService.getInstance().getCountSearch("mac");
        Assert.assertEquals(iGet, 96);
    }
}


// docker run -it --rm --name my-maven-project --net=container:tata -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven maven:3.5.0-jdk-8-alpine mvn clean install

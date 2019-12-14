package com.lizijian.officeauto;


import com.lizijian.officeauto.Service.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.Executor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AyncTest implements Runnable{

    @Autowired
    AsyncService asyncService;

    @Autowired
    Executor executor;

    ArrayList<Integer> i = new ArrayList<>();
    Integer j = 5;

    @Test
    public void asyncTest() throws InterruptedException {

    }

    @Test
    public void runTest(){
        AyncTest ayncTest = new AyncTest();
        while (this.j>0){
            executor.execute(ayncTest);
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+j);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        j--;
    }
}

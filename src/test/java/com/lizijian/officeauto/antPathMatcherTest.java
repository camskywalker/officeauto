package com.lizijian.officeauto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.AntPathMatcher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class antPathMatcherTest {
    @Autowired
    AntPathMatcher antPathMatcher;

    @Test
    public void test(){
        String urI1 = "/courses/12/userrelation";
        String pattern1 = "/courses/{courseId}/*/{userId}";
        System.out.println(antPathMatcher.extractUriTemplateVariables(pattern1, urI1));
    }
}

package com.lizijian.officeauto.Config.aspect;

import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.utils.ResourcesAuthenticateUtils;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthenticateResourcesAspect {

    @Autowired
    ResourcesAuthenticateUtils resourcesAuthenticateUtils;

    private void flushAuthenticateResources(ProceedingJoinPoint joinPoint){
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        User user = (User) request.getAttribute("user");
        resourcesAuthenticateUtils.setRedisAuthenticateResources(user);
    }

    //可以把所有切点都配置到一个方法里面，但不允许换行，视觉观感很差，所以就多写重复代码了
    @Around("execution(* com.lizijian.officeauto.Controller.UserController.insertUser(..))")
    public Object interceptUserInsert(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        flushAuthenticateResources(joinPoint);
        return proceed;
    }

    @Around("execution(* com.lizijian.officeauto.Controller.UserController.deleteUser(..))")
    public Object interceptUserDelete(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = joinPoint.proceed();
        flushAuthenticateResources(joinPoint);
        return proceed;
    }

    @Around("execution(* com.lizijian.officeauto.Controller.CourseController.addCourse(..))")
    public Object interceptCourseInsert(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = joinPoint.proceed();
        flushAuthenticateResources(joinPoint);
        return proceed;
    }

    @Around("execution(* com.lizijian.officeauto.Controller.CourseController.deleteCourse(..))")
    public Object interceptCourseDelete(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = joinPoint.proceed();
        flushAuthenticateResources(joinPoint);
        return proceed;
    }

    @Around("execution(* com.lizijian.officeauto.Controller.KnowledgePointController.insertKnowledgePoint(..))")
    public Object interceptKnowledgePointInsert(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = joinPoint.proceed();
        flushAuthenticateResources(joinPoint);
        return proceed;
    }

    @Around("execution(* com.lizijian.officeauto.Controller.KnowledgePointController.deleteKnowledgePointById(..))")
    public Object interceptKnowledgePointDelete(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = joinPoint.proceed();
        flushAuthenticateResources(joinPoint);
        return proceed;
    }

}

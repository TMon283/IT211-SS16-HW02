package com.example.it211ss16hw02.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditLogAspect {

    @After("execution(* com.example.it211ss16hw02.controller.EmployeeController.getEmployees(..))")
    public void logEmployeeAccess(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String time = LocalDateTime.now().toString();

        System.out.printf("[AUDIT LOG] Tài khoản '%s' đã gọi hàm '%s' vào lúc %s%n",
                username, methodName, time);
    }
}


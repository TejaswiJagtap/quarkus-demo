package com.artcode.quarkus.custom_annotation;

//import org.eclipse.microprofile.jwt.JsonWebToken;
//
//import jakarta.annotation.Priority;
//import jakarta.inject.Inject;
//import jakarta.interceptor.AroundInvoke;
//import jakarta.interceptor.Interceptor;
//import jakarta.interceptor.InvocationContext;
//import jakarta.ws.rs.ForbiddenException;
//
//@Interceptor
//@AdminOnly
//@Priority(Interceptor.Priority.APPLICATION)
//public class AdminOnlyInterceptor {
//
//    @Inject
//    JsonWebToken jwt;
//
//    @AroundInvoke
//    public Object checkAdminRole(InvocationContext context) throws Exception {
//        if (jwt == null || !jwt.getGroups().contains("ADMIN")) {
//            throw new ForbiddenException("Access denied: Admins only");
//        }
//        return context.proceed();
//    }
//}

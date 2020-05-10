//package com.epoint.nvr.action;
//
//import java.lang.reflect.Method;
//import java.util.Set;
//
//import org.reflections.Reflections;
//import org.reflections.scanners.MethodAnnotationsScanner;
//import org.reflections.util.ClasspathHelper;
//import org.reflections.util.ConfigurationBuilder;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * org.reflections包扫描注解
// *
// * @author baB_hyf
// *
// */
//public class ReflectionJAR
//{
//    public static void main(String[] args) {
//        String scanPackage = "com.epoint.nvr.action.nvrmanage.";
//        // 设置扫描路径
//        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(scanPackage)).setScanners(new MethodAnnotationsScanner()));
//        // 扫描包内带有@RestController注解的所有方法集合
//        Set<Method> methods = reflections.getMethodsAnnotatedWith(RestController.class);
//        // []
//        System.out.println(methods);
//    }
//
//}

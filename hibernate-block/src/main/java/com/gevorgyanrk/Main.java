package com.gevorgyanrk;

import com.gevorgyanrk.configuration.AppConfig;
import com.gevorgyanrk.services.AppService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // TODO разобраться, почему комментим
        // ", products=" + products +
        // Комментим из за ленивой загрузки (Lazy), так как products не инициализирован.

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppService appService = context.getBean(AppService.class);

        System.out.println(appService.getProductById(1L));
        System.out.println(appService.getCustomerById(1L));

        System.out.println(appService.getCustomerProducts(1L));
        System.out.println(appService.getProductCustomers(1L));
    }
}

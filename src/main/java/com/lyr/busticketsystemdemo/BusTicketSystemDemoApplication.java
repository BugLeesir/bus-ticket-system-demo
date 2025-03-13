package com.lyr.busticketsystemdemo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yunruili
 */
@SpringBootApplication
@MapperScan("com.lyr.busticketsystemdemo.dao.mapper")
public class BusTicketSystemDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusTicketSystemDemoApplication.class, args);
    }

}

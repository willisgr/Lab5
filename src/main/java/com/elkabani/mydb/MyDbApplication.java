package com.elkabani.mydb;

import com.elkabani.mydb.entities.Address;
import com.elkabani.mydb.entities.Category;
import com.elkabani.mydb.entities.Product;
import com.elkabani.mydb.entities.User;
import com.elkabani.mydb.repositories.CategoryRepository;
import com.elkabani.mydb.repositories.ProductRepository;
import com.elkabani.mydb.repositories.UserRepository;
import com.elkabani.mydb.services.ProductService;
import com.elkabani.mydb.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class MyDbApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MyDbApplication.class, args);
    }

}

package com.example.demo;

import com.example.demo.dao.CustomerMapper;
import com.example.demo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootTest
public class testInsert {
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void insert() {
        Customer customer = new Customer();
        customer.setRealName("余明辉");
        customer.setAge(36);
        customer.setEmail("ymh@126.com");
        customer.setAddress("Roosevelt Way NE");
        customer.setSex("男");

        int num = this.customerMapper.insert(customer);

    }

    @Test
    public void update() {
        Customer customer = new Customer();
        customer.setCustomerId(2L);
        customer.setPhone("13823124142");

        int num = this.customerMapper.updateById(customer);
    }

    @Test
    public void testSelect() {
        System.out.println(customerMapper.selectById(1188));
    }
}

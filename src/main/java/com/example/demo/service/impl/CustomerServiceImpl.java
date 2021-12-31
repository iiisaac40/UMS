package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.dao.CustomerMapper;
import com.example.demo.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}

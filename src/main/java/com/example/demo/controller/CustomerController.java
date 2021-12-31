package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Customer;
import com.example.demo.service.ICustomerService;
import com.example.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    /**
     * 进入列表页
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "customer/customerList";
    }

    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(String realName, String phone, Long page, Long limit) {
        LambdaQueryWrapper<Customer> wrapper = Wrappers.<Customer>lambdaQuery()
                .like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
                .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
                .orderByDesc(Customer::getCustomerId);

        Page<Customer> myPage = customerService.page(new Page<>(page, limit), wrapper);

        return ResultUtil.buildPageR(myPage);

    }

    /**
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd() {
        return "customer/customerAdd";
    }

    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer) {

        return ResultUtil.buildR(customerService.save(customer));
    }


    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/customerUpdate";
    }

    /**
     * 修改客户
     * @param customer
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Customer customer) {
        return ResultUtil.buildR(customerService.updateById(customer));
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id) {
        return ResultUtil.buildR(customerService.removeById(id));
    }


    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/customerDetail";
    }
}

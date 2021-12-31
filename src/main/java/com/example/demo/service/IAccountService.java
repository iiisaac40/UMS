package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Resource;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
public interface IAccountService extends IService<Account> {
    LoginDTO login(String username, String password);

    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);

    Account getAccountById(Long id);
}

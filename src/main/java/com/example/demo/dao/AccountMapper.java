package com.example.demo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * 分页查询账号
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page, @Param(Constants.WRAPPER) Wrapper<Account> wrapper);

    Account selectAccountById(Long id);
}

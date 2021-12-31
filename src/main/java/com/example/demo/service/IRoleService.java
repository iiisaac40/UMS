package com.example.demo.service;

import com.example.demo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
public interface IRoleService extends IService<Role> {

    /**
     * 新增角色及其角色所具有的
     * @param role
     * @return
     */
    boolean saveRole(Role role);

    /**
     * 修改角色及其角色所具有的
     * @param role
     * @return
     */
    boolean updateRole(Role role);
}

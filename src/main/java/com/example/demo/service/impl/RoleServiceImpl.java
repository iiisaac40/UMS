package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.dao.RoleResourceMapper;
import com.example.demo.entity.Role;
import com.example.demo.dao.RoleMapper;
import com.example.demo.entity.RoleResource;
import com.example.demo.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRole(Role role) {
        save(role);
        Long roleId = role.getRoleId();

        List<Long> resourceIds = role.getResourceIds();
        if(CollectionUtils.isNotEmpty(resourceIds)) {
            for(Long resourceId : resourceIds) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(resourceId);

                roleResourceMapper.insert(roleResource);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(Role role) {
        updateById(role);
        Long roleId = role.getRoleId();

        roleResourceMapper.delete(Wrappers.<RoleResource>lambdaQuery()
                        .eq(RoleResource::getRoleId, roleId));

        List<Long> resourceIds = role.getResourceIds();
        if(CollectionUtils.isNotEmpty(resourceIds)) {
            for(Long resourceId : resourceIds) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(resourceId);

                roleResourceMapper.insert(roleResource);
            }
        }

        return  true;
    }
}

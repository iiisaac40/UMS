package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.vo.ResourceVO;
import com.example.demo.vo.TreeVO;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
public interface IResourceService extends IService<Resource> {
    /**
     *
     * @param roleId
     * @return
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);

    /**
     * 查询系统资源
     * @return
     */
    List<TreeVO> listResource(Long roleId, Integer flag);
}

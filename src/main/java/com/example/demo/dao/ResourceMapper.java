package com.example.demo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.vo.ResourceVO;
import com.example.demo.vo.TreeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询登陆人资源
     * @param wrapper
     * @return
     */

    List<ResourceVO> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);

    List<TreeVO> listResourceByRoleId(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper,
                                      @Param("roleId") Long roleId);
}

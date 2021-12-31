package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.entity.Resource;
import com.example.demo.dao.ResourceMapper;
import com.example.demo.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.ResourceVO;
import com.example.demo.vo.TreeVO;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        QueryWrapper<Resource> query = Wrappers.<Resource>query();
        query.eq("rr.role_id", roleId).isNull("re.parent_id").orderByAsc("re.sort");
        List<ResourceVO> resourceVOS = baseMapper.listResource(query);

        resourceVOS.forEach(r->{
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subWrapper = Wrappers.<Resource>query();
            subWrapper.eq("rr.role_id", roleId).eq("re.parent_id", resourceId).orderByAsc("re.sort");
            List<ResourceVO> subResourceVOS = baseMapper.listResource(subWrapper);
            if(CollectionUtils.isNotEmpty(subResourceVOS)) {
                r.setSubs(subResourceVOS);
            }
        });
        return resourceVOS;
    }

    @Override
    public List<TreeVO> listResource(Long roleId, Integer flag) {
        if (roleId == null) {
            LambdaQueryWrapper<Resource> wrapper = Wrappers.<Resource>lambdaQuery()
                    .isNull(Resource::getParentId)
                    .orderByDesc(Resource::getSort);
            List<Resource> resources = list(wrapper);

            List<TreeVO> treeVOS = resources.stream().map(r -> {
                TreeVO treeVO = new TreeVO();
                treeVO.setId(r.getResourceId());
                treeVO.setTitle(r.getResourceName());

                LambdaQueryWrapper<Resource> subWrapper = Wrappers.<Resource>lambdaQuery()
                        .eq(Resource::getParentId, r.getResourceId())
                        .orderByDesc(Resource::getSort);
                List<Resource> subResources = list(subWrapper);
                if (CollectionUtils.isNotEmpty(subResources)) {
                    List<TreeVO> children = subResources.stream().map(sub -> {
                        TreeVO subTreeVO = new TreeVO();
                        subTreeVO.setId(sub.getResourceId());
                        subTreeVO.setTitle(sub.getResourceName());
                        return subTreeVO;
                    }).collect(Collectors.toList());
                    treeVO.setChildren(children);
                }
                return treeVO;
            }).collect(Collectors.toList());
            return treeVOS;
        } else {
            QueryWrapper<Resource> query = Wrappers.<Resource>query();
            query.eq(flag == 1, "rr.role_id", roleId)
                    .isNull("re.parent_id").orderByAsc("re.sort");
            List<TreeVO> treeVOS = baseMapper.listResourceByRoleId(query, roleId);
            treeVOS.forEach(t -> {
                t.setChecked(false);
                Long id = t.getId();
                QueryWrapper<Resource> subWrapper = Wrappers.<Resource>query();
                subWrapper.eq(flag == 1, "rr.role_id", roleId)
                        .eq("re.parent_id", id).orderByAsc("re.sort");

                List<TreeVO> children = baseMapper.listResourceByRoleId(subWrapper, roleId);
                if (CollectionUtils.isNotEmpty(children)) {
                    t.setChildren(children);
                }
            });
            return treeVOS;
        }
    }
}

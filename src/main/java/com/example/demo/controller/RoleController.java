package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IResourceService;
import com.example.demo.service.IRoleService;
import com.example.demo.util.ResultUtil;
import com.example.demo.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author hao
 * @since 2021-04-13
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IAccountService accountService;

    @GetMapping("toList")
    public String toList() {
        return "role/roleList";
    }


    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(String roleName, Long page, Long limit) {
        QueryWrapper<Role> queryWrapper = Wrappers.<Role>query()
                .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                .orderByDesc("role_id");
        Page<Role> resultPage = roleService.page(new Page<>(page, limit), queryWrapper);

        return ResultUtil.buildPageR(resultPage);
    }

    @GetMapping("toAdd")
    public String toAdd() {
        return "role/roleAdd";
    }

    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Role role) {
        return ResultUtil.buildR(roleService.saveRole(role));
    }

    @GetMapping({"listResource", "listResource/{roleId}", "listResource/{roleId}/{flag}"})
    @ResponseBody
    public R<List<TreeVO>> listResource(@PathVariable(required = false) Long roleId,
                                        @PathVariable(required = false) Integer flag) {
        return R.ok(resourceService.listResource(roleId, flag));

    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id) {
        Integer count = accountService.lambdaQuery().eq(Account::getRoleId, id).count();
        if (count > 0) {
            return R.failed("有账号拥有该角色");
        }
        return ResultUtil.buildR(roleService.removeById(id));
    }

    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/roleDetail";
    }

    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/roleUpdate";
    }

    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Role role) {

        return ResultUtil.buildR(roleService.updateRole(role));
    }
}

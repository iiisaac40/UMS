package com.example.demo.dto;

import com.example.demo.entity.Account;
import lombok.Data;

@Data
public class LoginDTO {
    private String path; //重定向或跳转的路径
    private String error; //错误提示信息
    private Account account;
}

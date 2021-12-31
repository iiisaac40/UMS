package com.example.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResourceVO {
    private Long resourceId;
    private String resourceName;
    private String url;

    private List<ResourceVO> subs;
}

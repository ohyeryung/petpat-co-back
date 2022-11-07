package com.smile.petpat.category.controller;

import com.smile.petpat.category.service.PostCategoryService;
import com.smile.petpat.category.service.PostCategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post/")
public class PostCategoryController {

    private final PostCategoryServiceImpl postCategoryService;

    // PostCateGroup 등록
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public void registerCategoryGroup(){

    }


}
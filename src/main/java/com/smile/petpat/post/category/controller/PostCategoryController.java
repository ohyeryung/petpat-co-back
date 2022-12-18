package com.smile.petpat.post.category.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.post.category.service.PostCategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"category_api"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class PostCategoryController {

    private final PostCategoryServiceImpl postCategoryService;

    /**
     * 카테고리 그룹 리스트 조회
     * @return 성공 시 200 Success 와 함께 카테고리 그룹 목록 반환
     */
    @ApiOperation(value = "카테고리 그룹 리스트 조회", notes = "카테고리 그룹 리스트 조회")
    @RequestMapping(value = "/{postType}",method = RequestMethod.GET)
    public SuccessResponse getCategoryGroup(@PathVariable String postType){
       return SuccessResponse.success(postCategoryService.getCategoryGroup(postType));
    }


}

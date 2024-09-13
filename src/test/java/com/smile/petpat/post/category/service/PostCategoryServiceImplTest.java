package com.smile.petpat.post.category.service;

import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.dto.PostCategoryInfo;
import com.smile.petpat.post.category.repository.PetCategoryRepository;
import com.smile.petpat.post.category.repository.PostCategoryGroupRepository;
import com.smile.petpat.post.category.repository.TradeCategoryDetailRepository;
import com.smile.petpat.post.category.repository.TradeCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class PostCategoryServiceImplTest {

    @Autowired
    private PostCategoryServiceImpl postCategoryService;
    @Autowired
    private PostCategoryGroupRepository postCategoryGroupRepository;
    @Autowired
    private PetCategoryRepository petCategoryRepository;
    @Autowired
    private TradeCategoryRepository tradeCategoryRepository;


    @DisplayName("PostType으로 해당하는 categoryGroup을 조회한다.")
    @Test
    void selectCategoryGroupListByPostType() {
        // given
        String postTypeDescription = "중고거래";

        // when
        List<CategoryGroup> categoryGroupList = postCategoryService.getCategoryGroup(postTypeDescription);

        // then
        assertThat(categoryGroupList.stream()
                .map(categoryGroup -> categoryGroup.getCategoryGroupName()).collect(Collectors.toList()))
                .hasSize(3)
                .containsExactlyInAnyOrder("강아지","고양이","공통/기타");

    }

    @DisplayName("CateGoryGroupId에 해당하는 PetCateGoryList를 조회한다.")
    @Test
    void selectPetCateGoryListByCateGoryGroupId(){
        // given
        Long cateGoryGroupId = 1L;

        // when
        List<PostCategoryInfo.PetCategoryRes> petCategory = postCategoryService.getPetCategory(cateGoryGroupId);

        // then

    }

}
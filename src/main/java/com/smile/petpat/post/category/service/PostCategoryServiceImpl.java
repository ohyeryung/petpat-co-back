package com.smile.petpat.post.category.service;

import com.smile.petpat.post.category.domain.*;
import com.smile.petpat.post.category.repository.PetCategoryRepository;
import com.smile.petpat.post.category.repository.PostCategoryGroupRepository;
import com.smile.petpat.post.category.repository.TradeCategoryDetailRepository;
import com.smile.petpat.post.category.repository.TradeCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.smile.petpat.post.category.dto.PostCategoryInfo.*;

@Service
@RequiredArgsConstructor
public class PostCategoryServiceImpl implements PostCategoryService{

    private final PostCategoryGroupRepository postCategoryGroupRepository;
    private final PetCategoryRepository petCategoryRepository;
    private final TradeCategoryRepository tradeCategoryRepository;
    private final TradeCategoryDetailRepository tradeCategoryDetailRepository;

    @Override
    public List<CategoryGroup> getCategoryGroup(String postTypeDescription) {
        PostType postType = PostType.find(postTypeDescription);
        return postCategoryGroupRepository.findAllByPostType(postType);

    }

    @Override
    public List<PetCategoryRes> getPetCategory(Long categoryGroupId) {
        CategoryGroup categoryGroup = postCategoryGroupRepository.findByCategoryGroupId(categoryGroupId)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지않는 카테고리그룹입니다.")
                );
        List<PetCategory> petCategoryList = petCategoryRepository.findAllByCategoryGroup(categoryGroup);
        List<PetCategoryRes> petCategoryInfo = petCategoryList.stream()
                .map(PetCategoryRes::new).collect(Collectors.toList());
        return petCategoryInfo;
    }

    @Override
    public List<TradeCategoryRes> getTradeCategory(Long categoryGroupId) {
        CategoryGroup categoryGroup = postCategoryGroupRepository.findByCategoryGroupId(categoryGroupId)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지않는 카테고리그룹입니다.")
                );
        List<TradeCategory> tradeCategoryList = tradeCategoryRepository.findAllByCategoryGroup(categoryGroup);
        List<TradeCategoryRes> tradeCategoryInfo = tradeCategoryList.stream()
                .map(TradeCategoryRes::new).collect(Collectors.toList());
        return  tradeCategoryInfo;
    }

    @Override
    public List<TradeCategoryDetailRes> getTradeCategoryDetail(Long tradeCategoryId) {
        TradeCategory tradeCategory = tradeCategoryRepository.findByTradeCategoryId(tradeCategoryId)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지않는 중고거래 카테고리그룹입니다.")
        );
        List<TradeCategoryDetail> tradeCategoryDetailList = tradeCategoryDetailRepository.findAllByTradeCategory(tradeCategory);
        List<TradeCategoryDetailRes> tradeCategoryDetailInfo = tradeCategoryDetailList.stream()
                .map(TradeCategoryDetailRes::new).collect(Collectors.toList());
        return tradeCategoryDetailInfo;
    }
}

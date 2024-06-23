package com.smile.petpat.post.common.Address.service;

import com.smile.petpat.post.common.Address.Dto.AddressReqDto;
import com.smile.petpat.post.common.Address.domain.Address;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.trade.domain.TradeInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService{
    Address getAddress(AddressReqDto addressReqDto);
    RehomingPagingDto getRehomingsByAddress(AddressReqDto addressReqDto, Pageable pageable, String userEmail);
    TradeInfo.TradePagingListInfo getTradesByAddress(AddressReqDto addressReqDto, Pageable pageable, String userEmail);

    List<String> getProvinceList();

    List<String> getCityList(String province);

    List<String> getDistrictList(String province,String city);

    List<String> getTownsList(String province,String city, String district);
}

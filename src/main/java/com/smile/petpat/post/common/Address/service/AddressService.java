package com.smile.petpat.post.common.Address.service;

import com.smile.petpat.common.exception.CustomException;
import com.smile.petpat.common.response.ErrorCode;
import com.smile.petpat.post.common.Address.Dto.AddressReqDto;
import com.smile.petpat.post.common.Address.domain.Address;
import com.smile.petpat.post.common.Address.repository.AddressRepository;
import com.smile.petpat.post.common.CommonUtils;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.trade.domain.TradeInfo;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CommonUtils commonUtils;

    public Address getAddress(AddressReqDto addressReqDto){
        return addressRepository.findAddressByProvinceAndCityAndDistrictAndTown(addressReqDto.getProvince(),
                        addressReqDto.getCity(), addressReqDto.getDistrict(), addressReqDto.getTown())
                .orElseThrow(()->new CustomException(ErrorCode.ILLEGAL_ADDRESS_NOT_EXIST));
    }

    public RehomingPagingDto getRehomingsByAddress(AddressReqDto addressReqDto, Pageable pageable, String userEmail){
        // User && Address Check
        User user = commonUtils.userChk(userEmail);
        Address address = getAddress(addressReqDto);
        return new RehomingPagingDto(addressRepository.getRehomingsByAddress(address,pageable,user.getUserEmail()));
    }

    public TradeInfo.TradePagingListInfo getTradesByAddress(AddressReqDto addressReqDto, Pageable pageable, String userEmail){
        // User && Address Check
        User user = commonUtils.userChk(userEmail);
        Address address = getAddress(addressReqDto);
        return new TradeInfo.TradePagingListInfo(addressRepository.getTradesByAddress(address,pageable,user.getUserEmail()));
    }

}

package com.smile.petpat.post.common.Address.Dto;

import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import com.smile.petpat.post.trade.domain.TradeCommand;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressReqDto {
    private String province;
    private String city;
    private String district;
    private String town;

    public AddressReqDto(RehomingCommand rehomingCommand){
        this.province = rehomingCommand.getProvince();
        this.city = rehomingCommand.getCity();
        this.district =rehomingCommand.getDistrict();
        this.town = rehomingCommand.getTown();
    }

    public AddressReqDto(TradeCommand tradeCommand){
        this.province = tradeCommand.getProvince();
        this.city = tradeCommand.getCity();
        this.district= tradeCommand.getDistrict();
        this.town = tradeCommand.getTown();
    }
}

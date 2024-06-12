package com.smile.petpat.post.common.Address.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.post.common.Address.Dto.AddressReqDto;
import com.smile.petpat.post.common.Address.service.AddressService;
import com.smile.petpat.user.service.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "AddressController",description = "주소 API입니다.")
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    @Operation(summary = "주소에 해당하는 분양글 조회", description = "주소에 해당하는 분양글 조회")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public SuccessResponse getRehomingsByAddress(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                 @ModelAttribute @Valid AddressReqDto addressReqDto,
                                                 @PageableDefault Pageable pageable){
        return SuccessResponse.success(addressService.getRehomingsByAddress(addressReqDto,pageable,userDetails.getUsername()));
    }
}

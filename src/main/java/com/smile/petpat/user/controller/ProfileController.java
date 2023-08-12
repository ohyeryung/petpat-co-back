package com.smile.petpat.user.controller;

import com.smile.petpat.common.response.SuccessResponse;
import com.smile.petpat.user.domain.ProfileService;
import com.smile.petpat.user.domain.UserModify;
import com.smile.petpat.user.dto.UserDto;
import com.smile.petpat.user.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final UserModify userModify;
    private final ProfileService profileService;

    /**
     * 프로필 수정
     * @return 성공 시 200 Success 반환
     */
    @RequestMapping(value="",method = RequestMethod.PUT)
    public SuccessResponse modifyProfile(@ModelAttribute UserDto.ModifyUserRequest request,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        userModify.modifyProfile(request,userDetails.getUser());
        return SuccessResponse.noDataSuccess("OK");
    }

    /**
     * 비밀번호 확인
     * @return 성공 시 200 Success 반환
     */
    @RequestMapping(value ="/password",method = RequestMethod.POST)
    public SuccessResponse checkPassword(@RequestBody UserDto.CheckPasswordRequest request,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        userModify.passwordCheck(request.getPassword(),userDetails.getUser());
        return SuccessResponse.noDataSuccess("OK");
    }


    /**
     * 비밀번호 수정
     * @return 성공 시 200 Success 반환
     */
    @RequestMapping(value ="/password",method = RequestMethod.PUT)
    public SuccessResponse modifyPassword(@RequestBody UserDto.ModifyPasswordRequest request,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails){
        userModify.modifyPassword(request,userDetails.getUser());
        return SuccessResponse.noDataSuccess("OK");
    }

    /**
     * 내가 쓴 분양 글 조회
     */
    @RequestMapping(value = "/rehoming", method = RequestMethod.GET)
    public SuccessResponse getMyRehoming(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                         @PageableDefault Pageable pageable){
        return SuccessResponse.success(profileService.getMyRehoming(userDetails.getUser(),pageable));
    }

    /**
     * 내가 쓴 판매 글 조회
     */
    @RequestMapping(value = "/trade", method = RequestMethod.GET)
    public SuccessResponse getMyTrade(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @PageableDefault Pageable pageable){
        return SuccessResponse.success("");
    }

    /**
     * 내가 남긴 댓글 조회
     */
    @RequestMapping(value = "/comment",method = RequestMethod.GET)
    public SuccessResponse getMyComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @PageableDefault Pageable pageable){
        return SuccessResponse.success(profileService.getMyComment(userDetails.getUser(),pageable));
    }
}

package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import com.smile.petpat.post.rehoming.domain.RehomingInfo;
import com.smile.petpat.post.rehoming.dto.RehomingResDto;
import com.smile.petpat.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RehomingService {
    void registerRehoming(User user, List<MultipartFile> rehomingImg, RehomingCommand rehomingCommand);
    List<RehomingInfo> listRehoming();
    RehomingResDto detailRehoming(Long postId);
    RehomingResDto detailRehomingForMember(Long postId, User user);
    RehomingResDto updateRehoming(User user, Long postId, RehomingCommand rehomingCommand, List<MultipartFile> rehomingImg);
    void deleteRehoming(User user, Long rehomingId);
}

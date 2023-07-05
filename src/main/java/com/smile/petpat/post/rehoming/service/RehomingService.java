package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.rehoming.dto.RehomingResDto;
import com.smile.petpat.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RehomingService {
    void registerRehoming(User user, RehomingCommand rehomingCommand);

    RehomingPagingDto listRehomingForMember(User user, Pageable pageable);

    RehomingPagingDto listRehoming(Pageable pageable);

    RehomingResDto detailRehomingForMember(Long postId, User user);

    RehomingResDto detailRehoming(Long postId);

    RehomingResDto updateRehoming(User user, Long postId, RehomingCommand rehomingCommand);

    void deleteRehoming(User user, Long postId);

    void updateStatusFinding(User user, Long postId);

    void updateStatusReserved(User user, Long postId);

    void updateStatusMatched(User user, Long postId);

}

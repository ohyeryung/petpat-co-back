package com.smile.petpat.post.common.views;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
import com.smile.petpat.post.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ViewsService {
    private final RehomingRepository rehomingRepository;
    private final TradeRepository tradeRepository;
    /*
     * 조회수 카운트 메소드 TODO : 중복 방지 검증 필요 */
    @Transactional
    public void updateViewCnt(Long postId, PostType postType) {
        if (postType == PostType.REHOMING) {
            rehomingRepository.updateViews(postId);
        } else {
            tradeRepository.updateViews(postId);
        }
    }
}
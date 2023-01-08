package com.smile.petpat.post.common;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.bookmarks.domain.Bookmark;
import com.smile.petpat.post.common.bookmarks.repository.BookmarkRepository;
import com.smile.petpat.post.common.likes.domain.Likes;
import com.smile.petpat.post.common.likes.repository.LikesRepository;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class CommonUtils {

    private final LikesRepository likesRepository;
    private final BookmarkRepository bookmarkRepository;

    public void userChk(User user){

    }

    // 게시글 좋아요 값 유무 확인
    public Boolean LikePostChk(Long postId, PostType postType, User user){
        return getLikePost(postId, postType, user) != null;
    }

    // 게시글 좋아요 조회
    public Likes getLikePost(Long postId, PostType postType, User user){
        return likesRepository.findUserLikeQuery(postId, postType.toString(), user.getId());
    }

    // 게시글 북마크 값 유무 확인
    public Boolean BookmarkPostChk(Long postId, PostType postType, User user) {
        return getBookmarkPost(postId, postType, user) !=null;
    }

    // 게시글 북마크 조회
    public Bookmark getBookmarkPost(Long postId, PostType postType, User user) {
        return bookmarkRepository.findUserBookmarkQuery(postId, postType.toString(), user.getId());
    }

    // 좋아요 북마크 반환값
    public HashMap<String,String> toggleResponseHashMap(Boolean result, int cnt, Long postId, String postType){
        HashMap<String,String> hs = new HashMap<>();

        hs.put("result",String.valueOf(result));
        hs.put("postType", postType);
        hs.put("postId", String.valueOf(postId));
        hs.put("cnt", String.valueOf(cnt));
        return hs;
    }
}

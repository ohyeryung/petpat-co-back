package com.smile.petpat.post.common.bookmarks.service;

import com.smile.petpat.user.domain.User;

import java.util.HashMap;

public interface BookmarkService {
    HashMap<String, String> BookmarkPost(String postType, Long postId, User user);
}

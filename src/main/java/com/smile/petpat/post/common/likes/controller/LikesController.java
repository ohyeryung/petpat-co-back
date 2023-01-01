package com.smile.petpat.post.common.likes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikesController {

    @RequestMapping(value = "/{postType}/{postId}",method = RequestMethod.POST)
    public void isLike(@PathVariable String postId, @PathVariable String postType){

    }

}

package com.social.project.controller;

import com.social.project.model.Like;
import com.social.project.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> addLike(
            @PathVariable Long postId,
            @RequestBody Like likeRequest
    ) {
        Like like = likeService.addLike(postId, likeRequest);
        return ResponseEntity.ok(like);
    }
}

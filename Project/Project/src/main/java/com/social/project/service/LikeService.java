package com.social.project.service;

import com.social.project.model.Like;
import com.social.project.model.Post;
import com.social.project.repository.LikeRepository;
import com.social.project.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    public Like addLike(Long postId, Like likeRequest) {
        // Find the post by ID
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        // Create new Like
        Like like = new Like();
        like.setUsername(likeRequest.getUsername());
        like.setPost(post);

        // Save and return
        return likeRepository.save(like);
    }
}

package com.social.project.service;

import com.social.project.exception.PostNotFoundException;
import com.social.project.model.Comment;
import com.social.project.model.Like;
import com.social.project.model.Post;
import com.social.project.repository.CommentRepository;
import com.social.project.repository.LikeRepository;
import com.social.project.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository,
                       CommentRepository commentRepository,
                       LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    // ===================== POSTS =====================

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    public Post updatePost(Long id, Post updatedPost) {
        Post post = getPostById(id);
        post.setUsername(updatedPost.getUsername());
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // ===================== COMMENTS =====================

    public Comment addComment(Long postId, Comment comment) {
        Post post = getPostById(postId);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPost(Long postId) {
        Post post = getPostById(postId);
        return post.getComments();
    }

    // ===================== LIKES =====================

    public Like addLike(Long postId, Like like) {
        Post post = getPostById(postId);

        // Prevent duplicate likes by same user
        boolean alreadyLiked = post.getLikes().stream()
                .anyMatch(l -> l.getUsername().equals(like.getUsername()));
        if (alreadyLiked) {
            throw new RuntimeException("User has already liked this post");
        }

        like.setPost(post);
        return likeRepository.save(like);
    }

    public long countLikes(Long postId) {
        Post post = getPostById(postId);
        return post.getLikes().size();
    }
}

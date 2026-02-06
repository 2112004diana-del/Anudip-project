package com.social.project.controller;

import com.social.project.model.Comment;
import com.social.project.model.Post;
import com.social.project.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    // ===================== POSTS =====================

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return service.createPost(post);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return service.getPostById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id,
                           @RequestBody Post post) {
        return service.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        service.deletePost(id);
        return "Post deleted successfully";
    }

    // ===================== COMMENTS =====================

    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable Long postId,
                              @RequestBody Comment comment) {
        return service.addComment(postId, comment);
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> getComments(@PathVariable Long postId) {
        return service.getCommentsByPost(postId);
    }
}

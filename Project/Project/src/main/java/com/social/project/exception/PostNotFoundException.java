package com.social.project.exception;

public class PostNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(Long id) {
        super("Post not found with id: " + id);
    }
}

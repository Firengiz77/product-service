package com.example.product_service.controller;

import com.example.product_service.dto.request.CommentRequestDto;
import com.example.product_service.dto.response.CommentResponseDto;
import com.example.product_service.exception.NoAccessException;
import com.example.product_service.exception.NoAuthenticationException;
import com.example.product_service.service.CommentService;
import com.example.product_service.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/admin")
    public List<CommentResponseDto> getAdminComments(@RequestHeader(value = "Authorization", required = false) String token){
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        String userRole = jwtTokenUtil.extractUserRole(token);
        if(userRole.equals("USER")) {
            throw new NoAccessException();
        }
        return commentService.getAdminComments();
    }

    @GetMapping("/{product_id}")
    public List<CommentResponseDto> getUserComments(@PathVariable Long product_id,@RequestHeader(value = "Authorization", required = false) String token){
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        return commentService.getUserComments(product_id);
    }

    @PostMapping
    public CommentResponseDto createComment(CommentRequestDto commentDto, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()) {
            throw new NoAuthenticationException();
        }
        return commentService.createComment(commentDto,token);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            throw new NoAuthenticationException();
        }
        String userRole = jwtTokenUtil.extractUserRole(token);
        if(userRole.equals("USER")) {
            throw new NoAccessException();
        }
        return commentService.deleteComment(id);
    }
}

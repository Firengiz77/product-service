package com.example.product_service.service;

import com.example.product_service.dto.request.CommentRequestDto;
import com.example.product_service.dto.response.CommentResponseDto;
import com.example.product_service.map.CommentMap;
import com.example.product_service.model.Comment;
import com.example.product_service.repository.CommentRepository;
import com.example.product_service.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMap commentMap;
    private final CommentRepository commentRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public List<CommentResponseDto> getUserComments(Long product_id) {
        return commentMap.toDto(commentRepository.findAllByProductId(product_id));
    }
    public List<CommentResponseDto> getAdminComments() {
        return commentMap.toDto(commentRepository.findAll());
    }

    public CommentResponseDto createComment(CommentRequestDto commentDto,String token) {
        String userId = jwtTokenUtil.extractUserId(token);
        Comment comment = Comment.builder()
                .username(userId)
                .description(commentDto.getDescription())
                .review(commentDto.getReview())
                .productId(commentDto.getProductId())
                .build();

        return commentMap.toCommentDto(commentRepository.save(comment));
    }

    public String deleteComment(Long id) {
        commentRepository.deleteById(id);
        return "Comment deleted";
    }

}

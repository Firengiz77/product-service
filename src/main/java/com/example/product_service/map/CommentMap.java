package com.example.product_service.map;

import com.example.product_service.dto.response.CommentResponseDto;
import com.example.product_service.model.Comment;

import java.util.List;

public interface CommentMap {
    CommentResponseDto toCommentDto(Comment comment);
    Comment toEntity(CommentResponseDto commentDto);
    List<CommentResponseDto> toDto(List<Comment> comments);
}

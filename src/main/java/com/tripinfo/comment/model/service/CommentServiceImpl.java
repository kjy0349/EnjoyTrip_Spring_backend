package com.tripinfo.comment.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tripinfo.board.mapper.BoardMapper;
import com.tripinfo.comment.model.CommentDto;
import com.tripinfo.comment.model.mapper.CommentMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	private final CommentMapper commentMapper;

	@Override
	public List<CommentDto> getCommentByNo(int articleNo) {
		return commentMapper.getCommentByNo(articleNo);
	}

	@Override
	public int writeComment(CommentDto comment) {
		return commentMapper.writeComment(comment);
	}

	@Override
	public int deleteComment(int commentNo) {
		return commentMapper.deleteComment(commentNo);
	}
}

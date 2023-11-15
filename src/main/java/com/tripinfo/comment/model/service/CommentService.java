package com.tripinfo.comment.model.service;

import java.util.List;


import com.tripinfo.comment.model.CommentDto;

public interface CommentService {
	
	List<CommentDto> getCommentByNo(int articleNo);
	int writeComment(CommentDto comment);
	int deleteComment(int commentNo);
}

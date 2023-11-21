package com.tripinfo.tripcomment.model.service;

import com.tripinfo.tripcomment.model.TripCommentDto;

import java.util.List;

public interface TripCommentService {
	
	List<TripCommentDto> getCommentByNo(int articleNo);
	int writeComment(TripCommentDto comment);
	int deleteComment(int commentNo);

	int updateSelectedStatus(int tripCommentNo);
}

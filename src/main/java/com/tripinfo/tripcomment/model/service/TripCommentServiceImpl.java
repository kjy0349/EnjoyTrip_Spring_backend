package com.tripinfo.tripcomment.model.service;

import com.tripinfo.tripcomment.model.TripCommentDto;
import com.tripinfo.tripcomment.model.mapper.TripCommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TripCommentServiceImpl implements TripCommentService {
	
	private final TripCommentMapper tripCommentMapper;

	@Override
	public List<TripCommentDto> getCommentByNo(int articleNo) {
		return tripCommentMapper.getCommentByNo(articleNo);
	}

	@Override
	public int writeComment(TripCommentDto comment) {
		return tripCommentMapper.writeComment(comment);
	}

	@Override
	public int deleteComment(int commentNo) {
		return tripCommentMapper.deleteComment(commentNo);
	}

	@Override
	public int updateSelectedStatus(int tripCommentNo){return tripCommentMapper.updateSelectedStatus(tripCommentNo);}
}

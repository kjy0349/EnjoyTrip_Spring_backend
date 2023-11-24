package com.tripinfo.board.service;

import com.tripinfo.board.dto.BoardDto;
import com.tripinfo.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDto> getAllArticles(int pgno, int pageSize) {
    	int start = pgno * pageSize - pageSize;
    	int listsize = pageSize;
    	
        return boardMapper.getAllArticles(start, listsize);
    }

    @Override
    public int insertArticle(BoardDto boardDto) { return boardMapper.insertArticle(boardDto);}

    @Override
    public BoardDto getArticleByNumber(int articleNo) {
    	boardMapper.updateHit(articleNo);
    	return boardMapper.getArticleByNumber(articleNo);
    }
    
    @Override
    public int deleteArticle(int articleNo) { return boardMapper.deleteArticle(articleNo);}
    
    @Override
    public int modifyArticle(BoardDto article) { return boardMapper.modifyArticle(article);}
    
    @Override
    public List<BoardDto> getArticlesBySubject(String subject) {return boardMapper.getArticlesBySubject(subject);}

	@Override
    public int getTotal() {
		return boardMapper.getTotal();
	}
}

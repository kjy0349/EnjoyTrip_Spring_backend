package com.tripinfo.board.service;

import com.tripinfo.board.dto.BoardDto;
import com.tripinfo.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BoardServiceImpl {
    private final BoardMapper boardMapper;

    public List<BoardDto> getAllArticles(int pgno, int pageSize) {
    	int start = pgno * pageSize - pageSize;
    	int listsize = pageSize;
    	
        return boardMapper.getAllArticles(start, listsize);
    }

    public int insertArticle(BoardDto boardDto) { return boardMapper.insertArticle(boardDto);}

    public BoardDto getArticleByNumber(int articleNo) {
    	boardMapper.updateHit(articleNo);
    	return boardMapper.getArticleByNumber(articleNo);
    }
    
    public int deleteArticle(int articleNo) { return boardMapper.deleteArticle(articleNo);}
    
    public int modifyArticle(BoardDto article) { return boardMapper.modifyArticle(article);}
    
    public List<BoardDto> getArticlesBySubject(String subject) {return boardMapper.getArticlesBySubject(subject);}

	public int getTotal() {
		return boardMapper.getTotal();
	}
}

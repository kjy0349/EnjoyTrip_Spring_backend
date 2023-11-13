package com.tripinfo.board.service;

import com.tripinfo.board.dto.BoardDto;
import com.tripinfo.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl {
    private final BoardMapper boardMapper;

    public List<BoardDto> getAllArticles() {
        return boardMapper.getAllArticles();
    }

    public int insertArticle(BoardDto boardDto) { return boardMapper.insertArticle(boardDto);}

    public BoardDto getArticleByNumber(int articleNo) {return boardMapper.getArticleByNumber(articleNo);}
    
    public int deleteArticle(int articleNo) { return boardMapper.deleteArticle(articleNo);}
    
    public int modifyArticle(BoardDto article) { return boardMapper.modifyArticle(article);}
    public List<BoardDto> getArticlesBySubject(String subject) {return boardMapper.getArticlesBySubject(subject);}
}

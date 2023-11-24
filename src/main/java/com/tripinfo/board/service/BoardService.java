package com.tripinfo.board.service;

import com.tripinfo.board.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> getAllArticles(int pgno, int pageSize);

    int insertArticle(BoardDto boardDto);

    BoardDto getArticleByNumber(int articleNo);

    int deleteArticle(int articleNo);

    int modifyArticle(BoardDto article);

    List<BoardDto> getArticlesBySubject(String subject);

    int getTotal();
}

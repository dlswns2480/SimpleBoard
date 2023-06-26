package com.study.board;

import com.study.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DeleteAllTest {
    @Autowired
    private BoardService boardservice;

    @Test
    public void deleteallTest(){
        boardservice.boardDelete(4);
    }
}

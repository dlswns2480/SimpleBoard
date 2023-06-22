package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public void write(Board board, MultipartFile file) throws  Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\file";
        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, filename); //projectPath에 filename이란 이름으로 저장
        file.transferTo(saveFile);

        board.setFilename(filename);
        board.setFilepath("/file/" + filename);

        boardRepository.save(board);
    }


    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearch(String searchKeword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeword, pageable);
    }

    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    //게시물 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

}

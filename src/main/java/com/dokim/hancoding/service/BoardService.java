package com.dokim.hancoding.service;


import com.dokim.hancoding.entity.Board;
import com.dokim.hancoding.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글작성
    public void wirte(Board board){
        boardRepository.save(board);
    }


    // 게시글 리스트 처리
    public List<Board> boardList(){
        return boardRepository.findAll();
    }


    // 특정 게시글 불러오기
    public Board boardview(Integer id){
        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }
}

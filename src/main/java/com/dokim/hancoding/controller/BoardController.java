package com.dokim.hancoding.controller;

import com.dokim.hancoding.entity.Board;
import com.dokim.hancoding.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    @ResponseBody
    public String hello(){
        return "hello world";
    }

    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardwritepro(Board board){

        System.out.println(board.getId());
        boardService.wirte(board);
        return "";
    }


    @GetMapping("/board/list")
    public String boardlist(Model model){

        model.addAttribute("list", boardService.boardList());

        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardview(Model model, Integer id){

        model.addAttribute("board", boardService.boardview(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list" ;
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable Integer id, Model model){

        model.addAttribute("board", boardService.boardview(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable Integer id, Board board){

        Board boardTemp = boardService.boardview(id);

        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.wirte(boardTemp);

        return "redirect:/board/list" ;
    }

}


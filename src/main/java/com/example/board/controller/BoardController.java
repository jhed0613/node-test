package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8000/board/write
    public String boardWriteForm(){
        return "boardWrite"; // " " 사이에 들어가는 값은 어떤 뷰 파일로 보내줄거냐.
    }

    @PostMapping("/board/writePro")
    public String boardWritePro(Board board){
        boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        // boardService.boardList() 가 실행되고 그 반환된 내용을 list 라는 이름으로 받아와서 넘기겠다...
        return "boardList";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model, @RequestParam("id") Integer id){ // 다시 불러올 때는 모델 써야함.
        // @RequestParam("as") 안의 값으로 http 에서 조건 선택 가능 ex /board/view?as=1
        model.addAttribute("board",boardService.boardView(id));
        return "boardView"; // templates 이름.
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("id") Integer id){
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model){
        model.addAttribute("board",boardService.boardView(id));
        return "boardModify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board){
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);
        return "redirect:/board/list";
    }
}

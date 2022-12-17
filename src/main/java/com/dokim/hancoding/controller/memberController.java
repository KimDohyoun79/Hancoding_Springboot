package com.dokim.hancoding.controller;


import com.dokim.hancoding.entity.MemberEntity;
import com.dokim.hancoding.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class memberController {
    @GetMapping("/")
    public String home(){
        return "home";
    }


    // 8. 글 작성 처리
    @GetMapping("/member/save")
    public String memberSave(){
        return "memberSave";
    }


    @Autowired
    private MemberService memberService;
    @PostMapping("/member/save2")
    public String memberSave2(MemberEntity memberEntity){
        System.out.println("memberEntity = " + memberEntity.toString());
        memberService.write(memberEntity);
        return "redirect:/";
    }


    // 9. 게시글 리스트
//    @GetMapping("/member/list")
//    public String memberList(Model model){
//        model.addAttribute("list", memberService.memberList());
//        return "memberList";
//    }


    // 10. 게시글 상세 페이지
    @GetMapping("/member/view")
    public String memberView(Integer id, Model model){
        model.addAttribute("view", memberService.findId(id));
        return "memberView";
    }


    // 11. 게시글 삭제
    @GetMapping("/member/delete")
    public String memberDelete(Integer id){
        memberService.del(id);
        return "redirect:/member/list";
    }


    // 12. 게시글 수정
    @GetMapping("/member/modify/{id}")
    public String memberModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("member", memberService.findId(id));
        return "memberModify";
    }

    @PostMapping("/member/modify2/{id}")
    public String memberModify2(@PathVariable("id") Integer id, MemberEntity memberTmp){

        MemberEntity member = memberService.findId(id);

        member.setEmail(memberTmp.getEmail());
        member.setPassword(memberTmp.getPassword());
        member.setName(member.getName());

        memberService.write(member);

        return "redirect:/member/list";
    }


    // 14. 페이징 처리
    @GetMapping("/member/list")
    public String memberList(Model model,@PageableDefault(page = 0, size = 10,sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<MemberEntity> list = memberService.memberList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1; //= pageable.getPageNumber();
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "memberList";
    }
}

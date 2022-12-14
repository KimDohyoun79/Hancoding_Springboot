package com.dokim.hancoding.controller;


import com.dokim.hancoding.entity.MemberEntity;
import com.dokim.hancoding.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/member/list")
    public String memberList(Model model){
        model.addAttribute("list", memberService.memberList());
        return "memberList";
    }


    @GetMapping("/member/view")
    public String memberView(Integer id, Model model){
        model.addAttribute("view", memberService.findId(id));
        return "memberView";
    }

    @GetMapping("/member/delete")
    public String memberDelete(Integer id){
        memberService.del(id);
        return "redirect:/member/list";
    }

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

}

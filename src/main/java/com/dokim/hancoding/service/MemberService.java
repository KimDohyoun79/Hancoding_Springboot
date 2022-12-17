package com.dokim.hancoding.service;


import com.dokim.hancoding.entity.MemberEntity;
import com.dokim.hancoding.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void write(MemberEntity member){
        memberRepository.save(member);
    }

    // 9. 게시글 리스트
//    public List<MemberEntity> memberList(){
//        return memberRepository.findAll();
//    }

    // 14. 페이징 처리
    public Page<MemberEntity> memberList(Pageable pageable){
        return memberRepository.findAll(pageable);
    }

    public MemberEntity findId(Integer id){
        return memberRepository.findById(id).get();
    }

    public void del(Integer id){
        memberRepository.deleteById(id);
    }

}

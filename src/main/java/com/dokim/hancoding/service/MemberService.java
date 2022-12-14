package com.dokim.hancoding.service;


import com.dokim.hancoding.entity.MemberEntity;
import com.dokim.hancoding.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<MemberEntity> memberList(){
        return memberRepository.findAll();
    }

    public MemberEntity findId(Integer id){
        return memberRepository.findById(id).get();
    }

    public void del(Integer id){
        memberRepository.deleteById(id);
    }

}

package com.dokim.hancoding.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Data
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;
    private String name;
}

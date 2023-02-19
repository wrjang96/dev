package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
public class Member {
    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;

        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);
        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);

        this.createdAt = createdAt == null? LocalDateTime.now(): createdAt;
    }

    final private Long id;

    private String nickname;
    final private String email;
    final private LocalDate birthday;
    final private LocalDateTime createdAt;

    final private static Long NAME_MAX_LETTER = 10L;

    void validateNickname(String nickname){
        Assert.isTrue(nickname.length() <= NAME_MAX_LETTER, "최대 길이를 초과함");

    }

    public void changeNickname(String other){
        Objects.requireNonNull(other);
        validateNickname(other);
        nickname = other;
    }





}

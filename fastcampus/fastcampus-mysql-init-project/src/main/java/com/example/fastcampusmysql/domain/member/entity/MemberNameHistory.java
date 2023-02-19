package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class MemberNameHistory {
    final private Long id;
    final private Long memberId;
    final private String nickname;
    final private LocalDateTime createdAt;
    // 정규화의 핵심은 중복을 제거하는 것 필드가 같다고 해서, 중복이 아니다.

    @Builder
    public MemberNameHistory(Long id, Long memberId, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null? LocalDateTime.now(): createdAt;
    }

}

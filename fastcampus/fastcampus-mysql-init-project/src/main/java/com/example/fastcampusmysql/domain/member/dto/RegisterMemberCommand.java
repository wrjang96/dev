package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;

public record RegisterMemberCommand (
        String email,
        String nickname,
        LocalDate birthdate
){

}
// 16부터 새로 사용됨, 레코드 선언 시 Getter Setter 자동으로 만들어주고 프로퍼티 형식으로 사용할 수 있게 됨 (get.email)

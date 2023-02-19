package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @DisplayName("패스워드를 초기화한다.")
    @Test
    void passwordTest(){
        //given
        User user = new User();
        //when
        user.initPassword(new CorrectFixedPasswordGenerator());
        user.initPassword(() -> "abcdefght");

        //then
        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 부합되지 않아 초기화되지않음 ")
    @Test
    void passwordTest2(){
        //given
        User user = new User();
        //when
        user.initPassword(new WrongFixedPasswordGenerator());
        //then
        assertThat(user.getPassword()).isNull();
    }
}

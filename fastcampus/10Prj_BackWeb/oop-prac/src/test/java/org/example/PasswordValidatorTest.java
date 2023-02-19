package org.example;


/*
비밀번호는 최소 8자 이상 12자 이하여야 한다.
비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외를 발생 시킨다.
경계조건에 대해 테스트 코드를 작성해야 한다.
*/

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class PasswordValidatorTest {

    @DisplayName("비밀번호는 최소 8자 이상 12자 이하여야 한다.")
    @Test
    void validatePasswordTest(){
        assertThatCode(()->PasswordValidator.validate("serverwizard1"))
                .doesNotThrowAnyException();

    }
    @DisplayName("비밀번호는 최소 8자 미만 12자 초과하는 경우 IllgalArgumentException 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"aabbccc","aabbc67891011"}) // 경계값
    void validatePasswordTest2(){
        assertThatCode(()->PasswordValidator.validate("aabb"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 최소 8자 이상 12자 이하여야 한다.");

    }
    /*
    테스트 코드 작성을 통해
    1. 문서화 역할
    2. 코드에 결함 발견
    3. 리팩토링 시 안정성 확보
    4. 테스트 하기 쉬운 코드 작성 시 더 낮은 결합도를 가진 설계를 얻을 수 있음
    */
}

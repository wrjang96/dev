package org.example.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;


/*
*
* 음식점에서 음식 주문하는 과정 구현
* 요구사항
* 1. 도메인을 구성하는 객체에는 어떤 것들이 있는지 고민
*     ㄴ 손님 메뉴판 돈가스 등
* 2. 객체들 간의 관계를 고민
*     ㄴ 손님 -- 메뉴판, 손님 -- 요리사, 요리사 -- 요리
* 3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기
* 4. 협력을 설계
* 5. 객체들을 포괄하는 타입에 적절한 책임을 할당
* 6. 구현하기
* */
public class Customertest {
    @DisplayName("메뉴 흐름에 해당하는 요리를 주문한다")
    @Test
    void orderTest(){
        Customer customer = new Customer();
        Menu menu = new Menu(List.of(new MenuItem("돈가스", 10000), new MenuItem("냉면", 7000)));
        Cooking cooking = new Cooking();

        assertThatCode(() -> customer.order("돈가스", menu, cooking)) // 1. 메뉴이름 2. 메뉴판 3. 요리사
                .doesNotThrowAnyException();
    }
}

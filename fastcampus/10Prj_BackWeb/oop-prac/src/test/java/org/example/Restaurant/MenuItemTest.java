package org.example.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class MenuItemTest {
    @DisplayName("메뉴 항목을 생성한다. ")
    @Test
    void createTest(){
        assertThatCode(() -> new MenuItem("돈가스",10000));
    }

}

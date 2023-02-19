package org.example.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CookingTest {
    @DisplayName("Make Food according to the menu")
    @Test
    void makeCookTest(){
        Cooking cooking = new Cooking();
        MenuItem menuItem = new MenuItem("돈가스", 10000);

        Cook cook = cooking.makeCook(menuItem);

        assertThat(cook).isEqualTo(new Cook("돈가스",10000));

    }

}

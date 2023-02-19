package org.example.Restaurant;

import java.util.List;
import java.util.Objects;

public class Menu{
    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;

    }

    public MenuItem choose(String name) {
        return this.menuItems.stream()
                .filter(menuItem -> menuItem.matches(name))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("잘못된 메뉴 이름입니다."));
    }


}

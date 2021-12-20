package com.kodilla.battleships;

import javafx.scene.Parent;

public class Ship extends Parent {
    public int type;
    public boolean vertical = true;
    private int health;

    public Ship(int type, boolean vertical) {
        this.type = type;
        this.vertical = vertical;
        health = type;
    }
}

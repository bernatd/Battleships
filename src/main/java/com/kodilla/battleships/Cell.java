package com.kodilla.battleships;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public int x, y;
    public Ship ship = null;
    private Board board;

    public Cell(int x, int y, Board board) {
        super(50, 50);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.TRANSPARENT);
        setStroke(Color.WHITE);
    }
}

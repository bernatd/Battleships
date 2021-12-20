package com.kodilla.battleships;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Board extends Parent {
    private final VBox cols = new VBox();
    private final boolean computer = false;
//    public int ships = 5;


    public Board(boolean computer) {
        for (int y = 0; y < 10; y++) {
            HBox rows = new HBox();
            for (int x = 0; x < 10; x++) {
                Cell c = new Cell(50, 50, this);
                rows.getChildren().add(c);
            }
            cols.getChildren().add(rows);
        }
        getChildren().add(cols);
    }

    public boolean placeShip(Ship ship, int x, int y) {
        if (isFreeLocation(ship, x, y)) {
            int length = ship.type;

            if (ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.ship = ship;
                    //                if (!computer) {
                    cell.setFill(Color.GREY);
                    cell.setStroke(Color.DARKGRAY);
                    //                }
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.ship = ship;
                    //                if (!computer) {
                    cell.setFill(Color.GREY);
                    cell.setStroke(Color.DARKGRAY);
                    //                }
                }
            }
            return true;
        }
        return false;
    }

    public Cell getCell(int x, int y) {
        return (Cell) ((HBox)cols.getChildren().get(y)).getChildren().get(x);
    }

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)};

        List<Cell> neighbors = new ArrayList<>();
        for (Point2D p : points) {
            if (isValidCell(p)) {
                neighbors.add(getCell((int) p.getX(), (int) p.getY()));
            }
        }
        return neighbors.toArray(new Cell[0]);
    }

    private boolean isValidCell(Point2D point) {
        return isValidCell(point.getX(), point.getY());
    }

    private boolean isValidCell(double x, double y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    private boolean isFreeLocation(Ship ship, int x, int y) {
        int length = ship.type;

        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidCell(x, i)) return false;

                Cell cell = getCell(x, i);
                if (cell.ship != null) return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidCell(x, i)) return false;
                    if (neighbor.ship != null) return false;
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!isValidCell(i, y)) return false;

                Cell cell = getCell(i, y);
                if (cell.ship != null) return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidCell(i, y)) return false;
                    if (neighbor.ship != null) return false;
                }
            }
        }
        return true;
    }
}

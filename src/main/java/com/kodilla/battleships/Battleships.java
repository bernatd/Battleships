package com.kodilla.battleships;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;


public class Battleships extends Application {
    private final Image imageBackground = new Image("file:src/main/resources/background_sky.png");
    private Random random = new Random();
    private final int noOfShips = 5;

    private Parent createGame() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true,
                true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        BorderPane root = new BorderPane();
        root.setBackground(background);
        root.setPrefSize(800,600);

        Board computerBoard = new Board(true);
        int type = noOfShips;
        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (computerBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        Board playerBoard = new Board(false);
//        playerBoard.placeShip(new Ship(5, true), 0, 0);
        type = noOfShips;
        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (playerBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        HBox hbox = new HBox(100, playerBoard, computerBoard);
        hbox.setAlignment(Pos.CENTER);
        root.setCenter(hbox);

        return root;
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createGame());
        stage.setTitle("Space battleships");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
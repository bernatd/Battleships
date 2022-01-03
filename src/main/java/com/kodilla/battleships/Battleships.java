package com.kodilla.battleships;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;


public class Battleships extends Application {
    private final Image imageBackground = new Image("file:src/main/resources/background_sky.png");
    private final Random random = new Random();
    private boolean running = false;
    private boolean computerTurn = false;
    private int shipsToPlace = 5;
    private Board computerBoard, playerBoard;

    private Parent createGame() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true,
                true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        BorderPane root = new BorderPane();
        root.setBackground(background);
        root.setPrefSize(800,600);

        computerBoard = new Board(true, event -> {
            if (!running) return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot) return;
            computerTurn = !cell.shoot();

            if (computerBoard.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (computerTurn) computerMove();
        });

        playerBoard = new Board(false, event -> {
            if (running) return;

            Cell cell = (Cell) event.getSource();
//            int type = shipsToPlace;
            System.out.println(cell);
            if (playerBoard.placeShip(new Ship(shipsToPlace, true), cell.x, cell.y)) {
//                if (--shipsToPlace == 0) {startGame();}
                --shipsToPlace;
            }
            if (shipsToPlace == 0) startGame();
            /*while (type > 0) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);

                if (playerBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                    type--;
                }
            }
            startGame();*/
        });

        HBox hbox = new HBox(100, playerBoard, computerBoard);
        hbox.setAlignment(Pos.CENTER);
        root.setCenter(hbox);

        return root;
    }

    private void computerMove() {
        while(computerTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);


            Cell cell = playerBoard.getCell(x,y);
            if (cell.wasShot) continue;

            computerTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOST");
                System.exit(0);
            }
        }
    }

    private void startGame() {
        int type = 5;
        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (computerBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }
        running = true;
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
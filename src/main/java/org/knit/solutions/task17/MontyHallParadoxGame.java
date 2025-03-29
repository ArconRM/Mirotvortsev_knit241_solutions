package org.knit.solutions.task17;

import java.util.Random;

public class MontyHallParadoxGame {
    private boolean didNotChangedChoiceWon = false;
    private boolean didChangedChoiceWon = false;

    public void play() {
        Random random = new Random();

        int playerChoice = random.nextInt(3);
        int carDoor = random.nextInt(3);

        int openedShitDoor = carDoor;
        while (openedShitDoor == playerChoice || openedShitDoor == carDoor) {
            openedShitDoor = random.nextInt(3);
        }

        didNotChangedChoiceWon = playerChoice == carDoor;

        int newPlayerChoice = random.nextInt(3);
        while (newPlayerChoice == openedShitDoor || newPlayerChoice == playerChoice) {
            newPlayerChoice = random.nextInt(3);
        }
        didChangedChoiceWon = newPlayerChoice == carDoor;
    }

    public boolean isDidNotChangedChoiceWon() {
        return didNotChangedChoiceWon;
    }

    public boolean isDidChangedChoiceWon() {
        return didChangedChoiceWon;
    }
}

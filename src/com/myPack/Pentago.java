package com.myPack;

import java.util.Scanner;

/**
 * This is the main class of the project.
 * It is a connection between the user inputs and the
 * game methods for playing a game.
 * This class contains all loops and scans we need.
 *
 */
public class Pentago {

    // This is for the output
    private int graphicType;

    /**
     * The main constructor of the class.
     *
     * @param graphicType the type of output
     */
    public Pentago(int graphicType){
        this.graphicType = graphicType;
    }
    /**
     * The only method of this class for playing the game.
     *
     * @param numberOfPlayers To chose the game mode
     */
    public void play(int numberOfPlayers, int gameLevel){

        // Creating an instance of the PlayOnBoard Class to access the board
        PlayOnBoard playOnBoard = new PlayOnBoard(graphicType, numberOfPlayers, gameLevel);

        // An instance of scanner library for getting the inputs
        Scanner scanner = new Scanner(System.in);

        // This variable is for getting the part (one from 4 parts of the board)
        int inputPart;

        // This variable is for getting the house in the part (one from 9 houses of the board)
        int inputHouse;

        // This variable is for choosing clockwise or counter clockwise
        int inputType;

        // Showing the guid first
        showGuid();

        // This method is for creating four new parts of the board
        playOnBoard.addingParts();

        // The main loop of the program
        while(true){

            // Showing the game
            playOnBoard.printTheGame();

            // Check if the game is over or not
            if(playOnBoard.checkTheGame())
                break;

            // Check if computer should play or not
            if(playOnBoard.getNumberOfPlayers() == 1 && playOnBoard.getPlayerTurn() == 2){
                playOnBoard.computerPlay();
                continue;
            }

            System.out.print("Player " + playOnBoard.getPlayerTurn() + " It Is Your Turn. You Are " + playOnBoard.getColor());

            /*
                This loop is for the player to chose a house.
                It won't break until we chose a valid house.

             */

            while(true){
                System.out.println(" (R is Red and B is Blue)");
                System.out.println("\nPut In Board :");

                System.out.print("Enter The Part Number > ");
                inputPart = scanner.nextInt();

                System.out.print("Enter The House Number > ");
                inputHouse = scanner.nextInt();

                if(inputHouse > 9 || inputHouse < 1 || inputPart > 4 || inputPart < 1){
                    System.out.println("Invalid input. Try Again.");
                    continue;
                }
                if(playOnBoard.putABall(inputPart, inputHouse))
                    break;

            }

            playOnBoard.printTheGame();

            if(playOnBoard.checkTheGame())
                break;

            /*
                This loop is for the player to chose a part to rotate.
                It won't break until we chose a part and a type.

             */

            while(true){
                System.out.println("\nRotate Board :");

                if(playOnBoard.checkPass()){

                    System.out.print("You Can Pass Rotation By Pressing 1 (If Not Press 2) > ");
                    inputType = scanner.nextInt();

                    if(inputType == 1)
                        break;
                }

                System.out.print("Enter The Part Number > ");
                inputPart = scanner.nextInt();

                System.out.print("Enter 1 For +90 Or 2 For -90 > ");
                inputType = scanner.nextInt();

                if(inputType > 2 || inputType < 1 || inputPart > 4 || inputPart < 1){
                    System.out.println("Invalid input. Try Again.");
                    continue;
                }
                if(playOnBoard.rotateBoard(inputPart, inputType))
                    break;

            }
        }

        System.out.println("Thanks For Playing");
    }

    /**
     * This method will only show a guid of the how to play the game.
     *
     */
    public void showGuid(){
        System.out.println("Hi welcome to Pentago. :)\n");
        System.out.println("Parts : ");
        System.out.println("   --- ---");
        System.out.println("  | 1 | 2 |");
        System.out.println("   --- ---");
        System.out.println("  | 3 | 4 |");
        System.out.println("   --- ---" +"\n");
        System.out.println("Houses : ");
        System.out.println("    --- --- ---");
        System.out.println("   | 1 | 2 | 3 |");
        System.out.println("    --- --- ---");
        System.out.println("   | 4 | 5 | 6 |");
        System.out.println("    --- --- ---");
        System.out.println("   | 7 | 8 | 9 |");
        System.out.println("    --- --- ---" + "\n");
        System.out.println("Enter your inputs : Parts + house");
        System.out.println("Enter your part and then 1 for clockwise rotation and 2 for counter clockwise rotation.");
        System.out.println("Enjoy Playing :) \n\n");
    }
}

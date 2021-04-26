package com.myPack;

import java.util.Random;

/**
 * This is the computer program for playing in a single mode.
 * This class will let the computer play the smart move.
 * Based on the difficulty of the game it will make a choice.
 *
 */
public class Computer {

    // Computer will give back an array which :
    // [0] == part - [1] == house - [2] == rotate Part - [3] == Type of rotate
    private int[] answer;

    // The Game level
    private int level;

    // This is for board game
    private int[][] board;

    /**
     * The main constructor of the program.
     *
     * @param board this for getting the whole board
     * @param level the difficulty of the game
     */
    public Computer(int[][] board, int level){
        this.level = level;
        answer = new int[4];
        this.board = board;
    }

    /**
     * This is a method to see how many houses do we have right now.
     *
     * @return the number of taken houses
     */
    private int getComputerHouse(int type){

        int number = 0;
        for(int y = 0; y < 6; y++)
            for(int x = 0; x < 6; x++)
                if(board[y][x] == type){
                    number++;
                }
        return number;
    }
    /**
     * This method will find and build the houses for computer to chose
     * the best house possible.
     */


    /**
     * This will play a random move each time.
     *
     */
    private void randomMove(){

        // Creating an instance of scanner and create random numbers
        Random random = new Random();
        int x, y;
        while(true){
            x = random.nextInt(6);
            y = random.nextInt(6);
            if(board[y][x] == 0) {
                changeForm(x, y);
                break;
            }
        }
        answer[2] = random.nextInt(4) + 1;
        answer[3] = random.nextInt(2) + 1;
    }

    /**
     * This method is for changing the board
     * coordinates with the valid input of the program.
     *
     * @param xRow the x coordinate
     * @param yCol the y coordinate
     */
    private void changeForm(int xRow, int yCol){

        if(xRow < 3 && yCol < 3){
            answer[0] = 1;
            answer[1] = yCol * 3 + xRow + 1;
        } else if(xRow > 2 && yCol < 3){
            answer[0] = 2;
            answer[1] = yCol * 3 + (xRow - 3) + 1;
        } else if(xRow < 3 && yCol > 2){
            answer[0] = 3;
            answer[1] = (yCol - 3) * 3 + xRow + 1;
        } else if(xRow > 2 && yCol > 2){
            answer[0] = 4;
            answer[1] = (yCol - 3) * 3 + (xRow - 3) + 1;
        }
    }

    /**
     * This is the main method of this class.
     *
     * @return the answers to the play on board class
     */
    public int[] makeMove(){

        if(level == 2) {

            // The start of the game.
            if(getComputerHouse(2) == 0) {

                // Finding the first house to put the ball in.
                // We check two houses cause one of them can be fulled by first move.
                if(board[4][4] == 0){
                    answer[0] = 4;
                    answer[1] = 5;
                    answer[2] = 1;
                    answer[3] = 1;
                } else {
                    answer[0] = 2;
                    answer[1] = 5;
                    answer[2] = 1;
                    answer[3] = 1;
                }
            } else {

                // Then we use in AI
                AI ai = new AI(board);
                answer = ai.answerMe();
            }
        } else if(level == 1)
            randomMove();
        return answer;
    }
}

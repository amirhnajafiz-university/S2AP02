package com.myPack;

import java.util.ArrayList;

/**
 * This is a class to help
 * the computer to find the best
 * rotation possible to get the best result.
 *
 */
public class AiRotationHelp {

    // Creating a board for ourselves
    private int[][] board;

    // The answers we want to get
    private int[] answer;

    // We save each situation with its score to get the best
    private ArrayList<AiRotationSheet> sheets;

    /**
     * The main constructor of this class.
     *
     * @param board give this class the board
     */
    public AiRotationHelp(int[][] board){
        sheets = new ArrayList<>();
        answer = new int[2];
        this.board = new int[6][6];
        this.board = board.clone();
    }

    /**
     * This will check and find the score of a rotation
     * based on the number of same type balls.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param line the direction
     * @return the score of this situation
     */
    public int giveTheScore(int x, int y, int line){

        // Finding each rotation score
        int score = 0;
        int maximum = 0;

        while(true) {

            // Make changes base on the line
            switch (line) {
                case 1:
                    y -= 1;
                    break;
                case 2:
                    y += 1;
                    break;
                case 3:
                    x += 1;
                    break;
                case 4:
                    x -= 1;
                    break;
                case 5:
                    x += 1;
                    y -= 1;
                    break;
                case 6:
                    x -= 1;
                    y += 1;
                    break;
                case 7:
                    x += 1;
                    y += 1;
                    break;
                case 8:
                    x -= 1;
                    y -= 1;
            }

            // Check the limits
            if(x < 0 || x > 5 || y < 0 || y > 5)
                break;
            if(board[y][x] == 2){
                score++;
            } else {
                if(score > maximum)
                    maximum = score;
                score = 0;
            }
        }

        return maximum;
    }

    /**
     * This method will rotate a part and will send it
     * to giveTheScore to give this situation a value.
     *
     * @param part the part we rotate
     * @param rotationType the type of the rotation
     */
    public void goInBoard(int part, int rotationType){

        // Each rotation point
        int result = 0;

        // Checking each 18 sequences
        for(int x = 0; x < 6; x++){
            result += giveTheScore(x, 0, 2);
        }
        for(int y = 0; y < 6; y++){
            result += giveTheScore(0, y, 3);
        }
        result += giveTheScore(0, 0, 7);
        result += giveTheScore(0, 5, 5);
        result += giveTheScore(1, 0, 7);
        result += giveTheScore(0, 1, 7);
        result += giveTheScore(0, 4, 5);
        result += giveTheScore(1, 5, 5);

        // Adding the results to the list
        AiRotationSheet aiRotationSheet = new AiRotationSheet(part, rotationType, result);
        sheets.add(aiRotationSheet);
    }

    /**
     * This method will rotate a part for us.
     *
     * @param part the part to rotate
     * @param rotate the type of the rotation
     */
    public void rotatePart(int part, int rotate) {
        // Same in AI

        int[] temp = new int[9];
        int y;
        int x;

        switch (part) {
            case 1:
                y = 0;
                x = 0;
                break;
            case 2:
                y = 0;
                x = 3;
                break;
            case 3:
                y = 3;
                x = 0;
                break;
            default:
                y = 3;
                x = 3;
        }

        temp[0] = board[y][x];
        temp[1] = board[y][x + 1];
        temp[2] = board[y][x + 2];
        temp[3] = board[y + 1][x];
        temp[4] = board[y + 1][x + 1];
        temp[5] = board[y + 1][x + 2];
        temp[6] = board[y + 2][x];
        temp[7] = board[y + 2][x + 1];
        temp[8] = board[y + 2][x + 2];


        if (rotate == 1) {
            board[y][x] = temp[6];
            board[y][x + 1] = temp[3];
            board[y][x + 2] = temp[0];
            board[y + 1][x] = temp[7];
            board[y + 1][x + 1] = temp[4];
            board[y + 1][x + 2] = temp[1];
            board[y + 2][x] = temp[8];
            board[y + 2][x + 1] = temp[5];
            board[y + 2][x + 2] = temp[2];
        } else {
            board[y][x] = temp[2];
            board[y][x + 1] = temp[5];
            board[y][x + 2] = temp[8];
            board[y + 1][x] = temp[1];
            board[y + 1][x + 1] = temp[4];
            board[y + 1][x + 2] = temp[7];
            board[y + 2][x] = temp[0];
            board[y + 2][x + 1] = temp[3];
            board[y + 2][x + 2] = temp[6];
        }
    }

    /**
     * This method will rotate each part and
     * then send it to goInBoard for checking.
     *
     */
    public void rotateParts(){

        // We rotate each part in two directions
        // and check their score.
        for(int part = 1; part < 5; part++){
            rotatePart(part, 1);
            goInBoard(part, 1);
            rotatePart(part, 2);
            rotatePart(part, 2);
            goInBoard(part, 2);
            rotatePart(part, 1);
        }
    }

    /**
     * This method will check all the houses and will
     * chose the high score rotation and will give it back.
     *
     * @return the rotation
     */
    public int[] answerMe(){
        rotateParts();

        // To get the maximum point rotation
        int max = 0;
        for(AiRotationSheet i : sheets){
            if(i.getScore() >= max){
                max = i.getScore();
                answer[0] = i.getPart();
                answer[1] = i.getRotationType();
            }
        }

        return answer;
    }
}

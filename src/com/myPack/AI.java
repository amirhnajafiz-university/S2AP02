package com.myPack;

import java.util.ArrayList;

/**
 * This is for computer to play smart.
 * This class will get the board and will
 * check all of the rotations and houses and
 * will chose the best house.
 *
 */
public class AI {

    // Getting the board
    private int[][] board;
    // We check every house of the board and keep in this class
    private ArrayList<AiHelpSheet> houses;

    /**
     * The main constructor of the class.
     *
     * @param board the board game
     */
    public AI(int[][] board){

        // Getting a copy of the board game
        this.board = new int[6][6];
        houses = new ArrayList<>();
        for(int y = 0; y < 6; y++){
            for(int x = 0; x < 6; x++){
                this.board[y][x] = board[y][x];
            }
        }

        // Now we go for checking
        searchBoard();
    }

    /**
     * This method will check the empty houses
     * and will send them to the goInBoard
     * to check the conditions.
     *
     */
    private void searchBoard(){

        // Check for empty houses
        for(int y = 0; y < 6; y++){
            for(int x = 0; x < 6; x++){
                if(board[y][x] == 0)
                    goInBoard(x, y);
            }
        }
    }

    /**
     * This method will rotate a chosen part.
     * We give this method two coordinates and this
     * method will check if the coordinates is in
     * the part that is going to rotate , then it will
     * give us the new coordinates.
     *
     * @param xRow the x coordinate
     * @param yCol the y coordinate
     * @param part the part we want to rotate
     * @param rotate the type of the rotation
     */
    private int[] rotatePart(int xRow, int yCol, int part, int rotate){

        // We use a temp array to save the part status and change it
        int[] temp = new int[9];

        // This is to return the new coordinates if the coordinates were change
        // in this rotation.
        int[] answer = {-1, -1};

        // Locating the start of each part
        int y = 0;
        int x = 0;
        switch(part){
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
            case 4:
                y = 3;
                x = 3;
        }

        // Save the part houses status
        temp[0] = board[y][x];
        temp[1] = board[y][x + 1];
        temp[2] = board[y][x + 2];
        temp[3] = board[y + 1][x];
        temp[4] = board[y + 1][x + 1];
        temp[5] = board[y + 1][x + 2];
        temp[6] = board[y + 2][x];
        temp[7] = board[y + 2][x + 1];
        temp[8] = board[y + 2][x + 2];

        // Change them based on the type of rotation
        if(rotate == 1){
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

        // This is to check the new house coordinates
        if(rotate == 1) {
            if (x == xRow && y == yCol) {
                answer[0] = x + 2;
                answer[1] = y;
            } else if (x + 1 == xRow && y == yCol) {
                answer[0] = x + 2;
                answer[1] = y + 1;
            } else if (x + 2 == xRow && y == yCol) {
                answer[0] = x + 2;
                answer[1] = y + 2;
            } else if (x == xRow && y + 1 == yCol) {
                answer[0] = x + 1;
                answer[1] = y;
            } else if (x + 1 == xRow && y + 1 == yCol) {
                answer[0] = x + 1;
                answer[1] = y + 1;
            } else if (x + 2 == xRow && y + 1 == yCol) {
                answer[0] = x + 1;
                answer[1] = y + 2;
            } else if (x == xRow && y + 2 == yCol) {
                answer[0] = x;
                answer[1] = y;
            } else if (x + 1 == xRow && y + 2 == yCol) {
                answer[0] = x;
                answer[1] = y + 1;
            } else if (x + 2 == xRow && y + 2 == yCol) {
                answer[0] = x;
                answer[1] = y + 2;
            }
        } else {
            if (x == xRow && y == yCol) {
                answer[0] = x;
                answer[1] = y + 2;
            } else if (x + 1 == xRow && y == yCol) {
                answer[0] = x;
                answer[1] = y + 1;
            } else if (x + 2 == xRow && y == yCol) {
                answer[0] = x;
                answer[1] = y;
            } else if (x == xRow && y + 1 == yCol) {
                answer[0] = x + 1;
                answer[1] = y + 2;
            } else if (x + 1 == xRow && y + 1 == yCol) {
                answer[0] = x + 1;
                answer[1] = y + 1;
            } else if (x + 2 == xRow && y + 1 == yCol) {
                answer[0] = x + 1;
                answer[1] = y;
            } else if (x == xRow && y + 2 == yCol) {
                answer[0] = x + 2;
                answer[1] = y + 2;
            } else if (x + 1 == xRow && y + 2 == yCol) {
                answer[0] = x + 2;
                answer[1] = y + 1;
            } else if (x + 2 == xRow && y + 2 == yCol) {
                answer[0] = x + 2;
                answer[1] = y;
            }
        }

        return answer;
    }

    /**
     * This method will give the coordinates of an empty house
     * and will check 5 situations.
     * No rotation at all, rotate part 1 to part 4.
     * And will check that house in this situations and for each
     * of them it looks for the score in the house.
     *
     * @param xRow the x coordinate
     * @param yCol the y coordinate
     */
    private void goInBoard(int xRow, int yCol){

        // This is for saving the changes
        int[] saveTheChange;

        // First we check the house without any rotation
        searchTheLines(xRow, yCol, xRow, yCol, 0, 0);

        // Then we start rotating the parts
        for(int part = 1; part < 5; part++){
            // Rotate +90
            saveTheChange = rotatePart(xRow, yCol, part, 1);

            // Check if the coordinates have changed or not
            if(saveTheChange[0] == -1)
                searchTheLines(xRow, yCol, xRow, yCol, part, 1);
            else
                searchTheLines(saveTheChange[0], saveTheChange[1], xRow, yCol, part, 1);

            // Back to normal
            rotatePart(xRow, yCol, part, 2);

            // Rotate -90
            saveTheChange = rotatePart(xRow, yCol, part, 2);

            if(saveTheChange[0] == -1)
                searchTheLines(xRow, yCol, xRow, yCol, part, 2);
            else
                searchTheLines(saveTheChange[0], saveTheChange[1], xRow, yCol, part, 2);

            // Back to normal
            rotatePart(xRow, yCol, part, 1);
        }
    }

    /**
     * In here we go in each 8 directions looking for
     * our type balls and opposite type balls.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param xRow the const x coordinate
     * @param yCol the const y coordinate
     * @param part the part we rotate
     * @param rotate the rotation type
     */
    private void searchTheLines(int x, int y, int xRow, int yCol, int part, int rotate){

        // The two ints bellow are for each turn check line
        int sameTypeSequence = 0;
        int oppTypeSequence = 0;

        // The two ints bellow are for finding the maximum sequence
        int finalscore = 0;
        int finalOppScore = 0;

        for(int line = 1; line < 9; line += 2){
            // Looking for the same type
            sameTypeSequence += checkLine(x, y, line, 2);
            sameTypeSequence += checkLine(x, y, line + 1, 2);
            if(sameTypeSequence > finalscore) {
                finalscore = sameTypeSequence;
                sameTypeSequence = 0;
            }

            // Looking for the opp type
            oppTypeSequence += checkLine(x, y, line, 1);
            oppTypeSequence += checkLine(x, y, line + 1, 1);
            if(oppTypeSequence > finalOppScore) {
                finalOppScore = oppTypeSequence;
                oppTypeSequence = 0;
            }
        }

        // Adding the check result to the list
        AiHelpSheet temp = new AiHelpSheet(xRow, yCol, finalscore, finalOppScore, part, rotate);
        houses.add(temp);
    }

    /**
     * This method will pick a direction and will look for the
     * type of the balls we want till it reaches the end of the board
     * or the opposite type of the ball we are looking for.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param line the direction
     * @param type the type we are looking for
     * @return the score of how many we found
     */
    private int checkLine(int x, int y, int line, int type){

        // This is the most sequence in a direction
        int result = 0;

        while(true){

            // First we do changes based on the line direction
            switch(line){
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
                    break;
            }

            // Checking the limits first
            if(x < 0 || x > 5 || y < 0|| y > 5)
                break;

            if(board[y][x] == type){
                result++;
            } else {
                break;
            }
        }

        return result;
    }

    /**
     * This method will call all of the methods in this class
     * and will return the best house and rotation in an array.
     *
     * @return the array of answers
     */
    public int[] answerMe(){

        // Just a flag to see if we found any house or not
        boolean found = false;

        // We give back the answers by a array
        int[] answer = new int[4];

        // This int is to find the maximum score for each type
        int maxScore = 0;


        for(AiHelpSheet i : houses){
            if(i.offType == 4 && i.sameType < 4){
                if(i.offType > maxScore) {
                    answer[0] = i.part;
                    answer[1] = i.house;
                    answer[2] = i.rotatePart;
                    answer[3] = i.rotateType;
                    found = true;
                    maxScore = i.offType;
                }
            }
        }

        // Reset the score
        maxScore = 0;

        if(!found){
            for(AiHelpSheet i : houses){
                if(i.sameType > maxScore) {
                    answer[0] = i.part;
                    answer[1] = i.house;
                    answer[2] = i.rotatePart;
                    answer[3] = i.rotateType;
                    maxScore = i.sameType;
                }
            }
        }

        // This happens when the maximum point house
        // happened in no rotation
        if(answer[3] == 0) {
            AiRotationHelp aiRotationHelp = new AiRotationHelp(board);
            int[] temp = aiRotationHelp.answerMe();
            answer[2] = temp[0];
            answer[3] = temp[1];
        }

        return answer;
    }
}

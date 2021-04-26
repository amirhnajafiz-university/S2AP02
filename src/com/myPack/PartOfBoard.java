package com.myPack;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is one part of the 4 parts of the main board.
 * We keep the status of the part in here and some methods for
 * putting a ball and rotating the part 90' clockwise or converse.
 */
public class PartOfBoard {

    // An array list for the balls in that part
    private ArrayList<Ball> playerBalls;

    // This array is for the part 9 houses status we need for Print class
    private int[] partStatus;

    // The number of the part
    private int partNumber;

    /**
     * The main constructor of the program.
     *
     * @param partNumber one number from 1 to 4
     */
    public PartOfBoard(int partNumber){
        this.partNumber = partNumber;
        partStatus = new int[9];
        for(int i = 0; i < 9; i++)
            partStatus[i] = 0;
        playerBalls = new ArrayList<>();
    }

    /**
     * A getter method for getting the number of the part.
     *
     * @return the part number
     */
    public int getPartNumber() {
        return partNumber;
    }

    /**
     * A getter method for getting the status of this part.
     *
     * @return the list of the balls of that part
     */
    public ArrayList<Ball> getPlayerBalls() {
        return playerBalls;
    }

    /**
     * A getter method for getting the status of this part
     * In a board.
     *
     * @return the array of status
     */
    public int[] getPartStatus() {
        return partStatus;
    }

    /**
     * This method will check if the space for putting the ball in
     * is free and if it was it place it.
     *
     * @param type the type of the ball
     * @param index the house of the part
     * @return true or false
     */
    public boolean addBall(int type, int index){

        // Creating an instance of the Ball class
        Ball ball = new Ball(type, getPartNumber(), index);

        if (partStatus[index - 1] != 0) {
            System.out.println("You can't put it here. Try Again.");
            return false;
        }

        partStatus[index - 1] = type;
        playerBalls.add(ball);
        return true;
    }

    /**
     * This method is to rotate the part 90' clockwise or converse.
     * 1 is for clockwise and 2 is for converse.
     *
     * @param type 1 or 2
     * @return -1 or 0 or 10 ( for invalid and valid and pass status )
     */
    public int rotatePart(int type){

        // This is for check the pass
        int isEqual = -1;

        // Need a holder array
        int[] tempKeeper = new int[9];

        // Divided into two parts for clockwise or converse
        if(type == 1){

            // Clockwise rotate
            tempKeeper[0] = partStatus[6];
            tempKeeper[1] = partStatus[3];
            tempKeeper[2] = partStatus[0];
            tempKeeper[3] = partStatus[7];
            tempKeeper[4] = partStatus[4];
            tempKeeper[5] = partStatus[1];
            tempKeeper[6] = partStatus[8];
            tempKeeper[7] = partStatus[5];
            tempKeeper[8] = partStatus[2];

        } else if(type == 2){

            // Counter clockwise rotate
            tempKeeper[0] = partStatus[2];
            tempKeeper[1] = partStatus[5];
            tempKeeper[2] = partStatus[8];
            tempKeeper[3] = partStatus[1];
            tempKeeper[4] = partStatus[4];
            tempKeeper[5] = partStatus[7];
            tempKeeper[6] = partStatus[0];
            tempKeeper[7] = partStatus[3];
            tempKeeper[8] = partStatus[6];

        } else {
            System.out.println("Invalid input.");
            return isEqual;
        }

        if(Arrays.equals(partStatus, tempKeeper))
            return isEqual + 11;

        // Replacing new array with old array
        partStatus = tempKeeper;

        changeBallPlace(type);
        return isEqual + 1;
    }

    /**
     * This method is for changing the balls x and y
     * coordinates.
     *
     * @param type clockwise or counter clockwise
     */
    private void changeBallPlace(int type){

        for(Ball i : playerBalls){
            switch (i.getHouse()){
                case 1:
                    if(type == 2)
                        i.resetPlace(getPartNumber(), 7);
                    else
                        i.resetPlace(getPartNumber(), 3);
                    break;
                case 2:
                    if(type == 2)
                        i.resetPlace(getPartNumber(), 4);
                    else
                        i.resetPlace(getPartNumber(), 6);
                    break;
                case 3:
                    if(type == 2)
                        i.resetPlace(getPartNumber(), 1);
                    else
                        i.resetPlace(getPartNumber(), 9);
                    break;
                case 4:
                    if(type == 2)
                        i.resetPlace(getPartNumber(), 8);
                    else
                        i.resetPlace(getPartNumber(), 2);
                    break;
                case 6:
                    if(type == 2)
                        i.resetPlace(getPartNumber(), 2);
                    else
                        i.resetPlace(getPartNumber(), 8);
                    break;
                case 7:
                    if(type == 2)
                        i.resetPlace(getPartNumber(), 9);
                    else
                        i.resetPlace(getPartNumber(), 1);
                    break;
                case 8:
                    if(type == 2)
                        i.resetPlace(getPartNumber(), 6);
                    else
                        i.resetPlace(getPartNumber(), 4);
                    break;
                case 9:
                    if(type == 2)
                        i.resetPlace(getPartNumber(), 3);
                    else
                        i.resetPlace(getPartNumber(), 7);
                    break;
            }
        }
    }
}

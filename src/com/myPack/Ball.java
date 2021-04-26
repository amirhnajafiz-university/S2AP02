package com.myPack;

import java.util.Objects;

/**
 * This class is a player single ball that he or
 * she will put it on the board.
 * This class was created basically for the Computer class
 * to find an algorithm to play as a computer in a single mode.
 *
 */
public class Ball {

    // This type is for 1st or 2nd player to differ their balls
    private int type;

    // The x coordinate for Computer Class
    private int xRow;

    // The y coordinate for Computer Class
    private int yCol;

    // This is the house of the ball in the part.
    // We need it for BoardPart Class
    private int house;

    /**
     * the main constructor of the Class.
     *
     * @param type neither 1 or 2 for 1st or 2nd player
     * @param part a part from the 4 parts of the board
     * @param house a house from 9 houses of a part
     */
    public Ball(int type, int part, int house){
        this.type = type;
        this.house = house;
        findCoordinate(part, house);
    }

    /**
     * this method will get the part and the house and
     * will find the coordinates in a 6 * 6 table.
     *
     * @param part a part from the 4 parts of the board
     * @param house a house from 9 houses of a part
     */
    public void findCoordinate(int part, int house){

        if(part == 1 || part == 3)
            xRow = house - 1;

        if(part == 2 || part == 4)
            xRow = 3 + house - 1;

        if(part == 1 || part == 2){
            if(house < 4)
                yCol = 0;
            if(house > 3 && house < 7)
                yCol = 1;
            if(house > 6)
                yCol = 2;
        }

        if(part == 3 || part == 4){
            if(house < 4)
                yCol = 3;
            if(house > 3 && house < 7)
                yCol = 4;
            if(house > 6)
                yCol = 5;
        }
    }

    /**
     * A getter method for type value.
     *
     * @return the type of the ball
     */
    public int getType() {
        return type;
    }

    /**
     * A getter method for the house of the ball.
     *
     * @return the number of the house
     */
    public int getHouse() {
        return house;
    }

    /**
     * This method will change the ball place in rotation.
     *
     * @param part the number of part
     * @param newHouse the new house place in the part
     */
    public void resetPlace(int part, int newHouse){
        this.house = newHouse;
        findCoordinate(part, newHouse);
    }

    /**
     * This equal override is to control the place of
     * putting new balls.
     * We don't want to have two of them in one house.
     *
     * @param o the object we want to compare
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return xRow == ball.xRow &&
                yCol == ball.yCol;
    }

    /**
     *
     * @return the key of the object in hash table
     */
    @Override
    public int hashCode() {
        return Objects.hash(xRow, yCol);
    }
}

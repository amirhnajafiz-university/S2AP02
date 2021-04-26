package com.myPack;

/**
 * This class is to help the computer to play
 * and find houses and keep their data.
 * Since this class is only useful in the AI Class and everything
 * in there is private then no one can access here.
 * And if it does it can not change inside of the AI AiHelpSheet.
 * So we dont't use private, everything is just public.
 *
 */
public class AiHelpSheet {

    // Keeping the house coordinates
    int xRow;
    int yCol;

    // Keeping the scores
    int sameType;
    int offType;

    // Keeping the answers
    int part;
    int house;
    int rotatePart;
    int rotateType;

    /**
     * The main constructor of the class.
     *
     * @param xRow the x coordinate
     * @param yCol the y coordinate
     * @param sameType the score of the type
     * @param offType the score of the opposite type
     * @param rotatePart the part we rotate
     * @param rotateType the rotation type
     */
    public AiHelpSheet(int xRow, int yCol, int sameType, int offType, int rotatePart, int rotateType){
        this.xRow = xRow;
        this.yCol = yCol;
        this.sameType = sameType;
        this.offType = offType;
        this.rotatePart = rotatePart;
        this.rotateType = rotateType;
        findPartAndHouse();
    }

    /**
     * This method is for changing the board
     * coordinates into part and houses.
     *
     */
    private void findPartAndHouse(){
        if(xRow < 3 && yCol < 3){
            part = 1;
            house = yCol * 3 + xRow + 1;
        } else if(xRow > 2 && yCol < 3){
            part = 2;
            house = yCol * 3 + (xRow - 3) + 1;
        } else if(xRow < 3 && yCol > 2){
            part = 3;
            house = (yCol - 3) * 3 + xRow + 1;
        } else if(xRow > 2 && yCol > 2){
            part = 4;
            house = (yCol - 3) * 3 + (xRow - 3) + 1;
        }
    }
}

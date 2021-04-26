package com.myPack;

import java.util.ArrayList;

/**
 * this is a superclass for the Print and Check classes and
 * it changes the parts to a 6 * 6 2D array.
 *
 */
public class Board {

    // This is main array of this board which is a 2D board
    protected int[][] board;

    // We needed to have the four parts of the board in here
    protected ArrayList<PartOfBoard> partOfBoards;

    // This is for running in CMD
    protected int graphicType;

    /**
     * The main constructor of the Class.
     *
     */
    public Board(int graphicType){
        this.graphicType = graphicType;

        // We empty the board first just to make sure
        board = new int[6][6];
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 6; j++)
                board[j][i] = 0;
        partOfBoards = new ArrayList<>();
    }

    /**
     * this method will get the four parts to create the board for printing and checking.
     *
     * @param v the varagrs way to send the parts
     */
    public void addingParts(PartOfBoard...v){
        for(int i = 0; i < v.length; i++)
            partOfBoards.add(v[i]);
    }

    /**
     * This method will get the 4 parts and will create a 2D array.
     *
     */
    public void createBoard(){

        /*
            This is a bad algorithm to put the status parts in the
            2D array.
            Couldn't find any better way to do this.
         */

        for(int index = 0; index < 4; index++){

            int[] arrayTest = partOfBoards.get(index).getPartStatus();

            int k;
            int i = 0;
            int j;

            if(index % 2 == 0)
                k = 0;
            else
                k = 3;
            if(index == 0 || index == 1)
                j = 0;
            else
                j = 3;

            for(; i < 9; i++, k++){
                board[j][k] = arrayTest[i];
                if((i + 1) % 3 == 0)
                    j++;
                if(index % 2 == 0) {
                    if ((k + 1) % 3 == 0)
                        k = -1;
                } else {
                    if((k + 1) % 6 == 0)
                        k = 2;
                }
            }
        }
    }
}

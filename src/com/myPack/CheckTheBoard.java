package com.myPack;

import java.util.ArrayList;

/**
 * This class is for checking the board.
 * To see if we have a winner or the game is a draw or
 * we are not finished yer.
 */
public class CheckTheBoard extends Board {

    //We need this array list to see if we have a winner or not

    /*
        In this array list we have some integers ( 0 , 1 , 2 ).
        If we had 1 and 2 together this means we have a draw match.
        Neither 1 or 2 is single in this list that means we have a winner.
        We don't count zeros.
     */

    private ArrayList<Integer> winner = new ArrayList<>();

    /**
     * The main constructor of the Class
     *
     * @param graphicType the type of the output
     */
    public CheckTheBoard(int graphicType) {
        super(graphicType);
    }

    /**
     * This method will add integers in our winner list and we
     * use them in our other method.
     *
     */
    private void checkBoard(){

        // Clearing the array list
        winner.clear();

        // Horizontal
        for(int y = 0; y < 6; y++){
            if(board[y][1] == board[y][2] && board[y][2] == board[y][3] && board[y][3] == board[y][4])
                if(board[y][3] == board[y][0] || board[y][3] == board[y][5]) {
                    winner.add(board[y][3]);
                }
        }

        // Vertical
        for(int x = 0; x < 6; x++){
            if(board[1][x] == board[2][x] && board[2][x] == board[3][x] && board[3][x] == board[4][x])
                if(board[3][x] == board[0][x] || board[3][x] == board[5][x]) {
                    winner.add(board[3][x]);
                }
        }

        // Diagonal
        /*
            These six if will check the Diagonals to see
            if there is a match in there and to add it to the
            list.
         */

        // Two Mains
        if(board[1][1] == board[2][2] && board[2][2] == board[3][3] && board[3][3] == board[4][4]){
            if(board[3][3] == board[0][0] || board[3][3] == board[5][5]){
                winner.add(board[3][3]);
            }
        }

        if(board[4][1] == board[3][2] && board[3][2] == board[2][3] && board[2][3] == board[1][4]){
            if(board[2][3] == board[0][5] || board[2][3] == board[5][0]){
                winner.add(board[2][3]);
            }
        }

        // Four small 5 house diagonals
        if(board[1][0] == board[2][1] && board[2][1] == board[3][2] && board[3][2] == board[4][3] && board[4][3] == board[5][4])
            winner.add(board[1][0]);

        if(board[0][1] == board[1][2] && board[1][2] == board[2][3] && board[2][3] == board[3][4] && board[3][4] == board[4][5])
            winner.add(board[0][1]);

        if(board[4][0] == board[3][1] && board[3][1] == board[2][2] && board[2][2] == board[1][3] && board[1][3] == board[0][4])
            winner.add(board[4][0]);

        if(board[5][1] == board[4][2] && board[4][2] == board[3][3] && board[3][3] == board[2][4] && board[2][4] == board[1][5])
            winner.add(board[5][1]);
    }

    /**
     * This method will call the check the game and
     * will return some numbers in each case.
     *
     * @return a single number
     */
    public int checkGame(){
        /*
            4 --> The Board is full without a winner
            3 --> We have a draw
            2 --> 2nd Player won
            1 --> 1st Player won
            0 --> Game still on
         */

        // Check the board
        checkBoard();

        // Give the results
        if(winner.contains(1) && winner.contains(2))
            return 3;
        if(winner.contains(1))
            return 1;
        if(winner.contains(2))
            return 2;

        for(int y = 0; y < 6; y++){
            for(int x = 0; x < 6; x++){
                if(board[y][x] == 0)
                    return 0;
            }
        }
        return 4;
    }
}

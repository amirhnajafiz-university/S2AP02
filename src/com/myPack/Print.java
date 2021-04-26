package com.myPack;

import java.util.HashMap;

/**
 * This class is for printing the output
 * in cmd and it only has one method.
 * This is a subClass of our Board Class.
 *
 */
public class Print extends Board {

    // We need the players information
    private HashMap<Integer, Character> players;

    /**
     * The main constructor of the program.
     *
     * @param players the list of the players
     */
    public Print(int graphicType, HashMap<Integer, Character> players){
        super(graphicType);
        this.players = players;
    }

    /**
     * The only method for printing the board.
     *
     */
    public void printTheBoard(){
        System.out.print("\n\n");
        if(graphicType == 1)
            System.out.println("     -----------     -----------  ");
        else
            System.out.println("    " +  "\u001b[47m" + "                        " + "\u001b[0m");

        // A neted loop with a switch case
        for(int y = 0; y < 6; y++){

            if(graphicType == 1)
                System.out.print("    |");
            else
                System.out.print("    " + "\u001b[47m" + "  " + "\u001b[0m");

            for(int x = 0; x < 6; x++){
                switch (board[y][x]){
                    case 0:
                        if(graphicType == 1)
                            System.out.print(" " + "*" + " " + "|");
                        else
                            System.out.print("\u001b[42;1m" + " " + '\u20DD' + " " + "\u001B[0m");
                        break;
                    case 1:
                        if(graphicType == 1){
                            if (players.get(1) == 'R')
                                System.out.print(" " + "R" + " " + "|");
                            else
                                System.out.print(" " + "B" + " " + "|");
                        } else {
                            if (players.get(1) == 'R')
                                System.out.print("\u001B[41m" + " " + " " + " " + "\u001B[0m");
                            else
                                System.out.print("\u001B[44m" + " " + " " + " " + "\u001B[0m");
                        }
                        break;
                    case 2:
                        if(graphicType == 1){
                            if (players.get(2) == 'R')
                                System.out.print(" " + "R" + " " + "|");
                            else
                                System.out.print(" " + "B" + " " + "|");
                        } else {
                            if (players.get(2) == 'R')
                                System.out.print("\u001B[41m" + " " + " " + " " + "\u001B[0m");
                            else
                                System.out.print("\u001B[44m" + " " + " " + " " + "\u001B[0m");
                        }
                        break;
                }
                if(x == 2 && graphicType != 1){
                    System.out.print("\u001b[47m" + "  " + "\u001b[0m");
                } else if(x == 2){
                    System.out.print("   |");
                }
            }

            if(graphicType != 1){
                System.out.print("\u001b[47m" + "  " + "\u001b[0m");
            }
            System.out.print("\n");

            if(y == 2 && graphicType == 1)
                System.out.println("     --- --- ---     --- --- ---  ");
            else if(y == 2)
                System.out.println("    " +  "\u001b[47m" + "                        " + "\u001b[0m");

        }
        if(graphicType == 1)
            System.out.println("     -----------     -----------  ");
        else
            System.out.println("    " +  "\u001b[47m" + "                        " + "\u001b[0m");
        System.out.print("\n");
    }
}

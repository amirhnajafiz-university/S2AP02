package com.myPack;

import java.util.HashMap;
import java.util.Random;

/**
 * This class is a subclass of the Board class.
 * It contains the methods of the game and instances of
 * other classes and it is the only connection between the Classes
 * of the program.
 *
 */
public class PlayOnBoard extends Board {

    // This is to see whose turn is it
    private int playerTurn;

    // We need a list for the players
    private HashMap<Integer, Character> players;

    // The number of players
    private int numberOfPlayers;

    // This is for the level of the game
    private int gameLevel;

    /**
     * The constructor of this game.
     *
     */
    public PlayOnBoard(int graphicType, int numberOfPlayers, int gameLevel){
        super(graphicType);
        this.playerTurn = 1;
        this.numberOfPlayers = numberOfPlayers;
        this.gameLevel = gameLevel;
        players = new HashMap<>();
        players.put(1, randomTurn());
        if(players.get(1) == 'R')
            players.put(2, 'B');
        else
            players.put(2, 'R');
    }

    /**
     * This is a method for random color of players in the
     * starting of the game.
     *
     * @return A single Char
     */
    public char randomTurn(){
        String temp = "RB";
        Random r = new Random();
        int n = r.nextInt(2);
        return temp.charAt(n);
    }

    /**
     * A getter method for getting the number of players.
     *
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * This is a getter color for print Class.
     *
     * @return the color char of the player
     */
    public char getColor(){
        return players.get(playerTurn);
    }

    /**
     * This is a getter fo getting the turn.
     *
     * @return the player turn
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * This is the rebuild method of AddingParts.
     * Cause we are creating the board playground.
     *
     */
    public void addingParts() {
        for(int i = 1; i < 5; i++)
            partOfBoards.add(new PartOfBoard(i));
    }

    /**
     * This method will check the game for us to see what
     * status are we at.
     *
     * @return true or false
     */
    public boolean checkTheGame(){

        // Creating an instance of the Check class
        CheckTheBoard checkTheBoard = new CheckTheBoard(graphicType);
        checkTheBoard.addingParts(partOfBoards.get(0), partOfBoards.get(1), partOfBoards.get(2), partOfBoards.get(3));
        checkTheBoard.createBoard();

        // Check the game
        int result = checkTheBoard.checkGame();
        switch(result){
            case 3:
            case 4:
                System.out.println("This Game Was A Draw.");
                return true;
            case 1:
                System.out.println("1st Player Won.");
                return true;
            case 2:
                System.out.println("2nd Player Won.");
                return true;
            case 0:
                return false;
        }
        return false;
    }

    /**
     * This method will build an instance of the Print Class.
     * And will print the game for us.
     *
     */
    public void printTheGame(){

        Print print = new Print(graphicType, players);
        print.addingParts(partOfBoards.get(0), partOfBoards.get(1), partOfBoards.get(2), partOfBoards.get(3));
        print.createBoard();
        print.printTheBoard();
    }

    /**
     * This method will put a ball in the board.
     *
     * @param part the number of the part
     * @param house the house in that part
     */
    public boolean putABall(int part, int house){

        // To check who is puting the ball
        boolean result = partOfBoards.get(part - 1).addBall(playerTurn, house);

        // To change the turn
        if(result)
            playerTurn = (playerTurn == 1) ? 2 : 1;
        return result;
    }

    /**
     * This method will rotate a part for us.
     *
     * @param part the number of the part
     * @param type clockwise or counter clockwise
     */
    public boolean rotateBoard(int part, int type){
        if(type == 1){
            System.out.println("Part " + part + " : +90");
        } else {
            System.out.println("Part " + part + " : -90");
        }
        int result = partOfBoards.get(part - 1).rotatePart(type);
        return result == 0 || result == 10;
    }

    /**
     * This method is for checking if one part of the board have this
     * situation that with rotation it will not change.
     * If yes then we let the user to pass the rotation.
     *
     * @return true or false
     */
    public boolean checkPass(){

        int result;
        for(int part = 0; part < 4; part++){
            result = partOfBoards.get(part).rotatePart(1);
            if(result == 10){
                partOfBoards.get(part).rotatePart(2);
                return true;
            }
            partOfBoards.get(part).rotatePart(2);
            result = partOfBoards.get(part).rotatePart(2);
            if(result == 10){
                partOfBoards.get(part).rotatePart(1);
                return true;
            }
            partOfBoards.get(part).rotatePart(1);
        }
        return false;
    }

    /**
     * This is for single player mode , it is used for computer
     * to play against you.
     *
     */
    public void computerPlay(){

        createBoard();

        Computer computer = new Computer(board, gameLevel);

        int[] answer = computer.makeMove();
        putABall(answer[0], answer[1]);

        if(checkTheGame())
            return;

        rotateBoard(answer[2], answer[3]);
    }
}

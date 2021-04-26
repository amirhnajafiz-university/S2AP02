package com.myPack;

import java.util.Scanner;

/**
 * This is just a test for out program.
 *
 * @author AmirH
 * @version 0.4
 */
public class RunTest {

    public static void main(String[] args) {
        System.out.println("Hi welcome to Pentago.");

	    // Creating the scanner
        Scanner scanner = new Scanner(System.in);

        System.out.print("If You Are Running In Windows Command Line Enter 1 > ");
        int graphicType = scanner.nextInt();

        // Making an instance of the game
        Pentago pentago = new Pentago(graphicType);

        System.out.print("[1] Single Mode\n[2] Multi Player Mode\n>> ");
        // Getting the game mod
        int input = scanner.nextInt();

        int level = 1;
        // Getting the level of the game if it was necessary

        if(input == 1){
            System.out.print("[1] Easy\n[2] Hard\n>> ");
            level = scanner.nextInt();
        }

        // Start Playing
	    pentago.play(input, level);
    }
}

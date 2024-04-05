/*
    James Maner
    CPSC 1060: Programming Assignment 1
    Class Section 020
    3/31/2023
*/
import java.util.Scanner;

public class TicTacToe {

    /*
        *Description*
        @param c - the character variable that needs to be validated
        @return boolean - return true if the character is a digit between 1 and 3
    */
    //A helper method that validates the characters inputted by the user
    public static boolean validChar(char c)
    {
        boolean validChar = true;

        if (!(Character.isDigit(c)))
        {
            validChar = false;
        }

        if (Character.getNumericValue(c) < 1 || Character.getNumericValue(c) > 3)
        {
            validChar = false;
        }

        return validChar;
    }

    public static void main(String[] args)
    {
        //Declare all needed variables
        Scanner scnr = new Scanner(System.in);
        Board ticTac = new Board();
        String first; 
        String second;
        int row = 0;
        int col = 0;
        int turn = 0;
        boolean endGame = false;
        String playerSelection = " ";
        
        //Loop until the user decides not to play anymore
        while (!playerSelection.equalsIgnoreCase("N"))
        {
            //Reset the game and introduce the players to the example
            turn = 0;
            endGame = false;
            ticTac.clearBoard();
            System.out.println("Let's play Tic-Tac-Toe!\nWhen prompted, enter desired row and column numbers");
            System.out.println("Example: 1 3\n");
            ticTac.addMark('X', 1, 3);
            ticTac.printBoard();
            ticTac.clearBoard();
            System.out.println("\nLet's play!\nPlayer X starts!");

            //Loop until a winner is declared or a tie is found
            while (!endGame)
            {
                System.out.println();
                ticTac.printBoard();

                //If the turn is even, the X player will go, otherwise the O player will go
                if (turn % 2 == 0)
                {
                    //X player's turn
                    while (turn % 2 == 0 )
                    {
                        //Prompt the user for the placement of their marker
                        System.out.println("\nEnter row and column for player X");
                        first = scnr.next();
                        second = scnr.next();
                
                        //Validate that both entries are digits between 1 and 3
                        while (!((first.length() == 1) && (second.length() == 1)) || (!(validChar(first.charAt(0)) && validChar(second.charAt(0)))))
                        {
                            System.out.println("Please enter valid row and col numbers from 1 to 3:");
                            first = scnr.next();
                            second = scnr.next();
                        }

                        //Convert the characters to integers
                        row = Character.getNumericValue(first.charAt(0));
                        col = Character.getNumericValue(second.charAt(0));

                        //Check if the desired space is already filled by another marker
                        if (!ticTac.clearSpace(row, col))
                        {
                            System.out.println("That spot is full!");
                        }
                        
                        //Place the marker and end the turn if both entries have been verified
                        if (validChar(first.charAt(0)) && validChar(second.charAt(0)) && ticTac.clearSpace(row,col))
                        {
                            ticTac.addMark('X', row, col);
                            turn++;
                        }

                    }
                }
                else
                {
                    //O player's turn
                    while (turn % 2 == 1)
                    {
                        //Prompt the user for the placement of their marker
                        System.out.println("\nEnter row and column for player O");
                        first = scnr.next();
                        second = scnr.next();
                
                        //Validate that both entries are digits between 1 and 3
                        while (!((first.length() == 1) && (second.length() == 1)) || (!(validChar(first.charAt(0)) && validChar(second.charAt(0)))))
                        {
                            System.out.println("Please enter valid row and col numbers from 1 to 3:");
                            first = scnr.next();
                            second = scnr.next();
                        }

                        //Convert the characters to integers
                        row = Character.getNumericValue(first.charAt(0));
                        col = Character.getNumericValue(second.charAt(0));

                        //Check if the desired space is already filled by another marker
                        if (!ticTac.clearSpace(row, col))
                        {
                            System.out.println("That spot is full!");
                        }
                        
                        //Place the marker and end the turn if both entries have been verified
                        if (validChar(first.charAt(0)) && validChar(second.charAt(0)) && ticTac.clearSpace(row,col))
                        {
                            ticTac.addMark('O', row, col);
                            turn++;
                        }

                    }
                
                }
                
                //Check the board for the winner or a tie, either way end the game
                if (ticTac.checkWinner())
                {
                    System.out.println();
                    ticTac.printBoard();
                    if (turn % 2 == 1)
                    {
                        System.out.println("\nPlayer X WINS!\n");
                    }
                    else 
                    {
                        System.out.println("\nPlayer O WINS!\n");
                    }
                    endGame = true;
                }
                else if (ticTac.checkTie())
                {
                    System.out.println();
                    ticTac.printBoard();
                    System.out.println("\nIt's a TIE!\n");
                    endGame = true;
                }

            }

            //Prompt the user for their choice in playing another game
            System.out.println("Do you want to play again? Y or N");
            playerSelection = scnr.next();

            //Loop until the user selects either Y or N
            while (!(playerSelection.equalsIgnoreCase("Y") || playerSelection.equalsIgnoreCase("N")))
            {
                System.out.println("Please enter valid input: Y or N\n");
                System.out.println("Do you want to play again? Y or N");
                playerSelection = scnr.next();
            }

        }   
    
    }
}
/*
    James Maner
    CPSC 1060: Programming Assignment 1
    Class Section 020
    3/31/2023
*/
import java.util.Scanner;

public class Board {
    //Declare private fields
    private char[][] board = new char[3][7];
    private int i;
    private int j;

    //Constructor method creating a clear board to play on
    public Board()
    {
        for (i = 0; i < 3; i++)
        {
            for (j = 0; j < 7; j++)
            {
                if (j % 2 == 0)
                {
                    board[i][j] = '|';
                }
                else 
                {
                    board[i][j] = '_';
                }
            }
        }
    }

    //Printing out the board in the game
    public void printBoard()
    {
        for (i = 0; i < 3; i++)
        {
            for (j = 0; j < 7; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    /*
        *Description*
        @param mark - the mark being used to add to the board
        @param row - the row indicated to add the marker
        @param col - the column indicated to add the marker
        @return void - return nothing
    */
    //A method to add a marker to the board
    public void addMark(char mark, int row, int col)
    {
        //Adjust the location indicated to fit the array
        if (col == 2)
        {
            col = 3;
        }
        else if (col == 3)
        {
            col = 5;
        }
        row--;

        board[row][col] = mark;
    }

    //Returns all board spaces to their original states
    public void clearBoard()
    {
        //Reassign each array entry to their original states
        for (i = 0; i < 3; i++)
        {
            for (j = 0; j < 7; j++)
            {
                if (j % 2 == 0)
                {
                    board[i][j] = '|';
                }
                else 
                {
                    board[i][j] = '_';
                }
            }
        }
    }

    /*
        *Description*
        @param row - the row indicated to validate as a clear space
        @param col - the column indicated to validate as a clear space
        @return boolean - returns true if the location indicated is clear
    */
    //Checks whether or not the indicated space already has something there
    public boolean clearSpace(int row, int col)
    {
        boolean spaceClear = false;
        if (col == 2)
        {
            col = 3;
        }
        else if (col == 3)
        {
            col = 5;
        }
        row--;

        if (board[row][col] == '_')
        {
            spaceClear = true;
        }

        return spaceClear;
    }

    //Returns true if there is a row on the board in which all the rows are not empty and are the same
    public boolean checkRows()
    {
        boolean rowChecked = false;
        for (i = 0; i < 3; i++)
        {
            if (!this.clearSpace(i+1,1) && (board[i][1] == board[i][3]) && (board[i][3] == board[i][5]))
            {
                rowChecked = true;
            }
        }

        return rowChecked;
    }

    //Returns true if there is a column on the board in which all the rows are not empty and are the same
    public boolean checkColumns()
    {
        int x = 1;
        boolean colChecked = false;
        for (i = 1; i < 7; i+=2)
        {
            if (!this.clearSpace(1,x) && (board[0][i] == board[1][i]) && (board[1][i] == board[2][i]))
            {
                colChecked = true;
            }
            x++;
        }

        return colChecked;
    }

    //Returns true if there is a diagonal on the board in which all the rows are not empty and are the same
    public boolean checkDiagonals()
    {
        boolean diagChecked = false;
        if (!this.clearSpace(1,1) && (board[0][1] == board[1][3]) && (board[1][3] == board[2][5]))
        {
            diagChecked = true;
        }
        if (!this.clearSpace(1,3) && (board[0][5] == board[1][3]) && (board[1][3] == board[2][1]))
        {
            diagChecked = true;
        }

        return diagChecked;
    }

    //Returns true if there is a tie between the two players on the board
    public boolean checkTie()
    {
        boolean tie = false;
        int numMarks = 0;
        for (i = 1; i < 4; i++)
        {
            for (j = 1; j < 4; j++)
            {
                if (!this.clearSpace(i,j))
                {
                    numMarks++;
                }
            }
        }
        if (numMarks == 9)
        {
            tie = true;
        }

        return tie;
    }

    //Combines the board checking methods to check if the board has a winner
    public boolean checkWinner()
    {
        boolean winner = false;
        if (this.checkColumns())
        {
            winner = true;
        }
        if (this.checkRows())
        {
            winner = true;
        }
        if (this.checkDiagonals())
        {
            winner = true;
        }
        return winner;
    }
}

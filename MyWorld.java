import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Write a description of class MyWorld here.
 * Name:
 * Course:
 * Teacher:
 * Lab#1, Program#1
 * Date Last Modified: 3/20/2018
 */
public class MyWorld extends World
{
    private boolean playerOneTurn = true;
    private boolean messageShown = false;
    
    private String playerOneName = "";
    private String playerTwoName = "";
    
    private String[][] board = new String[3][3];
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     * @param there are no parameters
     * @return an object of type myWorld
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(720, 720, 1); 
        
        drawLines();
        
        for( int r = 0; r < board.length; r++ )
        {
            for( int c = 0; c < board[r].length; c++ )
            {
                board[r][c] = "";
            }
        }
        
        Greenfoot.start();
    }
    
    /**
     * drawLines draws the grid lines so that we can place the X's and O's inside for the game
     * 
     * @param there are no parameters
     * @return Nothing is returned
     */
    private void drawLines()
    {
        getBackground().setColor( Color.BLACK );
        
        for( int i = 240; i < getWidth(); i += 240 )
        {
            getBackground().drawLine(i, 0, i, getHeight() );
            getBackground().drawLine(0, i, getWidth(), i );
        }
    }
    
    /**
     * started this method stores the players name so that the player can choses hes or hers name while playing
     * 
     * @param there are no parameters
     * @return Nothing is returned
     */
    public void started()
    {
        if( messageShown == false )
        {
            playerOneName = JOptionPane.showInputDialog( null, "Player One, please enter your name:", "Player Name", JOptionPane.QUESTION_MESSAGE );
            
            playerTwoName = JOptionPane.showInputDialog( null, "Player Two, please enter your name:", "Player Name", JOptionPane.QUESTION_MESSAGE );
            
            messageShown = true;
        }
    }
    
    /**
     * act is the code that is run on each iterateion  of the act cycle
     * 
     * @param there are no parameters
     * @returned Nothing is returned
     */
    public void act()
    {
        displayBoard();
        Greenfoot.delay(10);
        
        if( checkPlayerOneWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations Player One!","Player One Wins", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
            messageShown = true;
        }
        else if( checkPlayerTwoWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations Player Two!", "Player Two Wins", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
            messageShown = true;
        }
        else if( checkBoardFilled() == true )
        {
            JOptionPane.showMessageDialog( null, "It is a draw!", "No One Wins", JOptionPane.PLAIN_MESSAGE );
            Greenfoot.stop();
            messageShown = true;
        }
        else
        {
            if( messageShown == false )
            {
                showTurn();
                messageShown = true;
            }
            
            checkMouseClick();
        }
    }
    
    /**
     * showTurn shows who's turn it is in game
     * 
     * @param there are no parameters
     * @return Nothing is returned
     */
    private void showTurn()
    {
        if( playerOneTurn == true )
        {
            JOptionPane.showMessageDialog( null, " player One's Turn", " player one turn", JOptionPane.PLAIN_MESSAGE );
        }
        else if( playerOneTurn == false )
        {
            JOptionPane.showMessageDialog( null, "Player Two's Turn", "Player Two Turn", JOptionPane.PLAIN_MESSAGE );
        }
    }
    
    /**
     * checkMouseClick checks if the user has click on a avaiable spot 
     * 
     * @param there are no parameters
     * @return Nothing is returned
     */
    private void checkMouseClick()
    {
        MouseInfo mouse;
        int rowNum;
        int columnNum;
        
        if( Greenfoot.mouseClicked(this) )
        {            
            mouse = Greenfoot.getMouseInfo();
            columnNum = mouse.getX()/(getWidth()/3 );
            rowNum = mouse.getY()/(getHeight()/3 );
            if( board[rowNum] [columnNum] == "")
            {
                if( playerOneTurn == true )
                {
                    board [rowNum] [columnNum] = "X";
                    playerOneTurn = false;
                    messageShown = false;
                }
                else
                {
                    board [rowNum] [columnNum] = "O";
                    playerOneTurn = true;
                    messageShown = false;
                }
            }
            else
            {
                 JOptionPane.showMessageDialog( null, "That Spot has already been taken", "Taken Spot", JOptionPane.PLAIN_MESSAGE );
            }
        }
    }
    
    /**
     * displayBoard displays the X's and O's in the boxs in the game
     * 
     * @param there are no parameters
     * @return Nothing is returned
     */
    private void displayBoard()
    {
        GreenfootImage toDisplay;
        
        for( int r = 0; r < board.length; r++ )
        {
            for( int c = 0; c < board[r].length; c++ )
            {
                toDisplay = new GreenfootImage( board [r][c], 100, Color.BLACK, new Color( 0, 0, 0, 0) );
                getBackground().drawImage( toDisplay, c* getWidth()/3 + (getWidth()/3 - toDisplay.getWidth())/2, r* getHeight()/3 + (getHeight()/3 - toDisplay.getHeight())/2 );
            }
        }
    }
    
    /**
     * checkPlayerOneWin checks if player one has won the game
     * 
     * @param there are no parameters 
     * @return returning a boolean
     */
    private boolean checkPlayerOneWin()
    {
        boolean playerOneWin = false;
        
        if( board [0][0] == "X" && board [0][1] == "X" && board [0][2] =="X" )
        {
            playerOneWin = true;
        }
        else if( board [1][0] == "X" && board [1][1] == "X" && board [1][2] == "X" )
        {
            playerOneWin = true;
        }
        else if( board [2][0] == "X" && board [2][1] == "X" && board [2][2] == "X" )
        {
            playerOneWin = true;
        }
        else if( board [0][1] == "X" && board [1][0] == "X" && board [2][0] == "X" )
        {
            playerOneWin = true;
        }
        else if( board [0][1] == "X" && board [1][1] == "X" && board [2][1] == "X" )
        {
            playerOneWin = true;
        }
        else if( board [0][2] == "X" && board [1][2] == "X" && board [2][2] == "X" )
        {
            playerOneWin = true;
        }
        else if( board [0][0] == "X" && board [1][1] == "X" && board [2][2] == "X" )
        {
            playerOneWin = true;
        }
        else if( board [0][2] == "X" && board [1][1] == "X" && board [2][0] == "X" )
        {
            playerOneWin = true;
        }
        return playerOneWin;
    }
    
    /**
     * checkPlayerTwoWin checks if player two has won the game
     * 
     * @param there are no parameters
     * @return returning a boolean
     */
    private boolean checkPlayerTwoWin()
    {
        boolean playerOneWin = false;
        
        if( board [0][0] == "O" && board [0][1] == "O" && board [0][2] =="O" )
        {
            playerOneWin = true;
        }
        else if( board [1][0] == "O" && board [1][1] == "O" && board [1][2] == "O" )
        {
            playerOneWin = true;
        }
        else if( board [2][0] == "O" && board [2][1] == "O" && board [2][2] == "O" )
        {
            playerOneWin = true;
        }
        else if( board [0][1] == "O" && board [1][0] == "O" && board [2][0] == "O" )
        {
            playerOneWin = true;
        }
        else if( board [0][1] == "O" && board [1][1] == "O" && board [2][1] == "O" )
        {
            playerOneWin = true;
        }
        else if( board [0][2] == "O" && board [1][2] == "O" && board [2][2] == "O" )
        {
            playerOneWin = true;
        }
        else if( board [0][0] == "O" && board [1][1] == "O" && board [2][2] == "O" )
        {
            playerOneWin = true;
        }
        else if( board [0][2] == "O" && board [1][1] == "O" && board [2][0] == "O" )
        {
            playerOneWin = true;
        }
        
        return playerOneWin;
    }
    
    /**
     * checkBoardFilled checks if the board of the game is full and on more spots are available
     * 
     * @param there are no parameters
     * @return returning a boolean 
     */
    private boolean checkBoardFilled()
    {
        boolean checkBoardFilled = true;
        
        for( int r = 0; checkBoardFilled == true && r < board.length; r++ )
        {
            for( int c = 0; checkBoardFilled == true && c < board[r].length; c++ )
            {
                if( board[r][c] == "")
                {
                    checkBoardFilled = false;
                }
            }
        }
        
        return checkBoardFilled;
    }
}

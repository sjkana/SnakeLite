

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Timer;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

public class MainClass extends GraphicsProgram implements ActionListener
{

    public Ball ball;

    private ArrayList<SnakePart> snakeBody;

    private int snakeX, snakeY, snakeWidth, snakeHeight;

    public Timer timer = new Timer(200, this);

    private boolean isPlaying, isGameOver;
    private int score, previousScore;
    private Scoreboard scoreLabel;


    public void run()
    {

        addKeyListeners();
    }


    public void drawSnake()
    {

    }

    public void keyPressed(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_UP:


            case KeyEvent.VK_DOWN:


            case KeyEvent.VK_LEFT:


            case KeyEvent.VK_RIGHT:


        }
    }


    private void redrawSnake()
    {
    }

    private void growSnake()
    {
    }

    private void moveUp()
    {
    }

    private void moveDown()
    {
    }

    private void moveLeft()
    {
    }

    private void moveRight()
    {
    }


    @Override
    public void actionPerformed(ActionEvent arg0)
    {

    }

    public static void main(String[] args)
    {
        new MainClass().start();
    }
}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import acm.graphics.*;
import acm.program.GraphicsProgram;

//starter code by emlag
//instructions by cishkcs
public class MainClass extends GraphicsProgram implements ActionListener
{

    public GOval food;

    private ArrayList<GRect> snakeBody;

    private int snakeWidth, snakeHeight;

    public Timer timer = new Timer(100, this);

    private boolean isGameOver = false;
    private boolean waitingForClick = false;
    private int score;
    private GLabel scoreLabel;
    boolean blockKey = false;
    boolean goingUp = false;
    boolean goingDown = false;
    boolean goingLeft = true;
    boolean goingRight = false;

    GRect background = new GRect(550, 300);

    public void keyPressed(KeyEvent keyPressed)
    {
        System.out.println("keypressed");
        blockKey = true;
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        if (blockKey)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP -> {
                    goingUp = true;
                    goingDown = false;
                    goingLeft = false;
                    goingRight = false;
                }
                case KeyEvent.VK_DOWN -> {
                    goingUp = false;
                    goingDown = true;
                    goingLeft = false;
                    goingRight = false;
                }
                case KeyEvent.VK_LEFT -> {
                    goingUp = false;
                    goingDown = false;
                    goingLeft = true;
                    goingRight = false;
                }
                case KeyEvent.VK_RIGHT -> {
                    goingUp = false;
                    goingDown = false;
                    goingLeft = false;
                    goingRight = true;
                }
            }
            blockKey = false;

        }
    }

    public void background()
    {
        background.setLocation(100, 100);
        background.setFillColor(Color.BLACK);
        background.setFilled(true);
        add(background);
    }
    public void mouseClicked(MouseEvent e) {
        if (waitingForClick) {
            System.out.println("Click detected");
            waitingForClick = false;
            removeAll();
            resetGame();
        }
    }

    public void run()
    {
        snakeBody = new ArrayList<>();
        addMouseListeners();
        createGCanvas();

        //adding the game background
        GRect rect = new GRect(getGCanvas().getWidth(), getGCanvas().getHeight());
        rect.setFillColor(Color.RED);
        rect.setFilled(true);
        add(rect);

        background();
        randomFood();
        setUpInfo();

        timer.start();
        drawSnake();
        addKeyListeners();

        isGameOver = false;
        waitingForClick = false;
    }

    public void randomFood()
    {
        Random rand = new Random();
        food = new Ball(50, 50, 15, 15);
        food.setFillColor(Color.red);
        food.setFilled(true);

        add(food);
        int randX = 100 + rand.nextInt(30)*15;
        int randY = 100 + rand.nextInt(20)*15;

        food.setLocation(randX, randY);
    }

    public void drawSnake()
    {
        snakeWidth = 15;
        snakeHeight = 15;

        //using a for loop to add 10 snake parts and adding it to the canvas
        for (int i = 0; i < 10; i++)
        {
            SnakePart part = new SnakePart(250 + i * 15, 340, snakeWidth, snakeHeight);
            part.setColor(Color.red);
            part.setFilled(true);

            //adding the part to the canvas
            add(part);

            //adding the part to the arraylist
            snakeBody.add(part);
        }
    }

    public void setUpInfo()
    {
        GLabel start = new GLabel("Click to begin.", (double) getWidth() / 2, ((double) getHeight() / 2));
        start.move(-start.getWidth() / 2, -start.getHeight());
        start.setColor(Color.WHITE);
        add(start);

        GLabel rules = new GLabel("Use arrow keys to change direction. Eat as much food as possible without hitting anything!", (double) getWidth() / 2, ((double) getHeight() / 2) + 60);
        rules.move(-rules.getWidth() / 2, -rules.getHeight());
        rules.setColor(Color.WHITE);
        add(rules);

        //adding the score label
        scoreLabel = new GLabel("Score : " + score, (double) getWidth() /2-30, 75);
        scoreLabel.setColor(Color.white);
        add(scoreLabel);

        //begin a game after click
        waitForClick();
        remove(start);
        remove(rules);
    }

    private void growSnake()
    {
        //creating a new snake part at the tail
        GRect rect = new GRect(snakeBody.get(snakeBody.size() - 1).getX() - snakeWidth, snakeBody.get(snakeBody.size() - 1).getY() - snakeHeight, snakeWidth, snakeHeight);
        rect.setFilled(true);
        rect.setColor(Color.red);
        snakeBody.add(rect);
    }

    private void redrawSnake()
    {
        //move each part to the location of the next part one by one
        for (int i = snakeBody.size() - 1; i > 0; i--)
        {
            snakeBody.get(i).setLocation(snakeBody.get(i - 1).getX(), (snakeBody.get(i - 1).getY()));
        }
        for (int i = 0; i < snakeBody.size(); i++)
        {
            add(snakeBody.get(i));
        }
    }

    private void moveUp()
    {
        redrawSnake();
        snakeBody.get(0).setLocation(snakeBody.get(0).getX(), snakeBody.get(0).getY() - 15);
    }

    private void moveDown()
    {
        redrawSnake();
        snakeBody.get(0).setLocation(snakeBody.get(0).getX(), snakeBody.get(0).getY() + 15);

    }

    private void moveLeft()
    {
        redrawSnake();
        snakeBody.get(0).setLocation(snakeBody.get(0).getX() - 15, snakeBody.get(0).getY());
    }

    private void moveRight()
    {
        redrawSnake();
        snakeBody.get(0).setLocation(snakeBody.get(0).getX() + 15, snakeBody.get(0).getY());
    }


    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if(!isGameOver)
        {
            scoreLabel.setLabel("Score : " + score);
            if (goingUp) {
                moveUp();
            }
            else if (goingDown)
            {
                moveDown();
            }
            else if (goingRight)
            {
                moveRight();
            }
            else if (goingLeft)
            {
                moveLeft();
            }
            if (intersectsWithFood())
            {
                score += 1;
                growSnake();

                remove(food);
                randomFood();
            }
            if (intersectsWithSnake()||wallHit())
            {
                isGameOver = true;
            }
        }
        else
        {
            timer.stop();
            playAgain();
        }

    }

    private void playAgain()
    {

        addMouseListeners();
        GLabel gameOverLabel = new GLabel("Game Over!", (double) getWidth() / 2, (double) getHeight() / 2);
        gameOverLabel.move(-gameOverLabel.getWidth() / 2, -gameOverLabel.getHeight());
        gameOverLabel.setColor(Color.white);
        add(gameOverLabel);

        GLabel playAgain = new GLabel("Click to exit.", (double) getWidth() / 2, (double) getHeight() /2 + 50);
        playAgain.move(-playAgain.getWidth() / 2, -playAgain.getHeight());
        playAgain.setColor(Color.white);
        add(playAgain);

        waitingForClick = true;
    }

    private void resetGame() {
        /*isGameOver = true;
        System.out.println("Resetting game...");

        removeAll();
        System.out.println("All components removed from the canvas");

        snakeBody.clear();
        System.out.println("Snake body cleared: " + snakeBody.size());


        score = 0;
        goingUp = false;
        goingDown = false;
        goingLeft = true;
        goingRight = false;
        System.out.println("Game state reset");

        run();
        System.out.println("Game restarted");

         */

        System.exit(0);
    }
    public boolean wallHit()
    {
        //using the boundaries of the game background
        if(snakeBody.get(0).getX()>635||snakeBody.get(0).getX()<115||snakeBody.get(0).getY()>385||snakeBody.get(0).getY()<115)
        {
            return true;
        }

        return false;
    }
    private boolean intersectsWithFood() {

        if (food.getBounds().intersects(snakeBody.get(0).getBounds()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private boolean intersectsWithSnake()
    {
        //finding the coordinates of the head
        GRect head = null;
        if ((!goingLeft && !goingDown && !goingUp) || goingRight)
        {
            head = new GRect(snakeBody.get(0).getX() + 15, snakeBody.get(0).getY() + 5, 5, 5);
        }
        else if (goingLeft)
        {
            head = new GRect(snakeBody.get(0).getX() - 5, snakeBody.get(0).getY() + 5, 5, 5);
        }
        else if (goingDown)
        {
            head = new GRect(snakeBody.get(0).getX() + 5, snakeBody.get(0).getY() + 15, 5, 5);
        }
        else
        {
            head = new GRect(snakeBody.get(0).getX() + 5, snakeBody.get(0).getY() - 5, 5, 5);
        }
        for (int i = 1; i < snakeBody.size(); i++)
        {
            if (head.getBounds().intersects(snakeBody.get(i).getBounds()))
            {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args)
    {
        new MainClass().start();
    }
}
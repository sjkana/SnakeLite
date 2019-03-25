/*
 * author : Natig Babayev
 * December, 2015
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

public class MainClass extends GraphicsProgram implements ActionListener {

	public GOval ball;

	private SnakePart[] snakeBody;

	private int snakeX, snakeY, snakeWidth, snakeHeight;

	public Timer timer = new Timer(200, this);

	private boolean isPlaying, isGameOver;
	private int score, previousScore;
	private GLabel scoreLabel;

	public void run() {


	}

    public void drawSnake() {

    }

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:


		case KeyEvent.VK_DOWN:


		case KeyEvent.VK_LEFT:


		case KeyEvent.VK_RIGHT:


		}
	}


	private void redrawSnake() {
	}

	private void growSnake() {
	}

	private void moveUp() {
	}

	private void moveDown() {
	}

	private void moveLeft() {
	}

	private void moveRight() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	public static void main(String[] args) {
		new MainClass().start();
	}
}
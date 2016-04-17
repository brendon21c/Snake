package com.Brendon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 * 
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {
	
	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint
	
	public Snake snake;
	private Kibble kibble;
	private Score score;
	public boolean isWalls = false;
	
	DrawSnakeGamePanel(GameComponentManager components){
		this.snake = components.getSnake();
		this.kibble = components.getKibble();
		this.score = components.getScore();
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        /* Where are we at in the game? 4 phases.. 
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

        gameStage = SnakeGame.getGameStage();
        
        switch (gameStage) {
			case SnakeGame.BEFORE_GAME: {
				displayInstructions(g);
				break;
			}
			case SnakeGame.DURING_GAME: {
				displayGame(g);
				break;
			}
			case SnakeGame.GAME_OVER: {
				displayGameOver(g);
				break;
			}
			case SnakeGame.GAME_WON: {
				displayGameWon(g);
				break;
        	}
        }
    }

	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!
		g.clearRect(100,100,350,350);
		g.drawString("YOU WON SNAKE!!!", 150, 150);
		
	}
	private void displayGameOver(Graphics g) {

		g.clearRect(100,100,350,350);
		g.drawString("GAME OVER", 150, 150);
		
		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();
		
		g.drawString("SCORE = " + textScore, 150, 250);
		
		g.drawString("HIGH SCORE = " + textHighScore, 150, 300);
		g.drawString(newHighScore, 150, 400);
		
		g.drawString("press a key to play again", 150, 350);
		g.drawString("Press q to quit the game",150,400);		
    			
	}

	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);

		if (isWalls == true) { // paints walls on the screen and catches an error of the method cannot be called

			try {
				buildWall(g);
			} catch (Exception e) {
				System.out.println("buildwall not working");

			}
		}

	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;
		
		g.clearRect(0, 0, maxX, maxY);

		g.setColor(Color.black);

		/*
		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){			
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){			
			g.drawLine(x, 0, x, maxY);
		} */
	}

	private void displayKibble(Graphics g) {

		//Draw the kibble in green
		g.setColor(Color.CYAN);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		g.fillRect(x+1, y+1, SnakeGame.squareSize-2, SnakeGame.squareSize-2);
		
	}

	public void buildWall(Graphics g) throws IOException { // builds wall and checks locations, could be better


		//BufferedImage img = ImageIO.read(new File("/Users/Brendon/Desktop/Java Class/Snake/img_5520.jpg"));

		g.setColor(Color.GRAY);

		//g.drawImage(img,2,2,this);

		g.fillRect(70, 70, snake.squareSize,snake.squareSize);
		g.fillRect(70, 105, snake.squareSize,snake.squareSize);
		g.fillRect(70, 140, snake.squareSize,snake.squareSize);

		g.fillRect(105,70,snake.squareSize,snake.squareSize);
		g.fillRect(140,70,snake.squareSize,snake.squareSize);
		g.fillRect(175,70,snake.squareSize,snake.squareSize);




		if (snake.snakeHeadX == 2 && snake.snakeHeadY == 2 || snake.snakeHeadX == 2 && snake.snakeHeadY == 3
				|| snake.snakeHeadX == 2 && snake.snakeHeadY == 4 || snake.snakeHeadX == 3 && snake.snakeHeadY == 2 ||
				snake.snakeHeadX == 4 && snake.snakeHeadY == 2 || snake.snakeHeadX == 5 && snake.snakeHeadY == 2) {

			snake.hitWall = true;
		}

	}


	private void displaySnake(Graphics g) {

		LinkedList<Point> coordinates = snake.segmentsToDraw();
		
		//Draw head in grey
		g.setColor(Color.green);
		Point head = coordinates.pop();
		g.fillRect((int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		
		//Draw rest of snake in black
		g.setColor(Color.BLACK);
		for (Point p : coordinates) {
			g.fillRect((int)p.getX(), (int)p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}
	}


	private void displayInstructions(Graphics g) {
		g.drawString("Choose a difficulty to start the game: ", 100, 100);
        g.drawString("Press the 1 key for easy difficulty!",100,150);
		g.drawString("Press the 2 key for medium difficulty!",100,200);
		g.drawString("Press the 3 key for hard difficulty!",100,250);

		g.drawString("Added features, choose before starting: ", 100, 275);
		g.drawString("Press q to quit the game",100,300);
		g.drawString("Press w to allow snake to wrap around board", 100,350);
		g.drawString("Press m to activate maze walls", 100,400);
    	}
}


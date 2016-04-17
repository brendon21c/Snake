package com.Brendon;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControls implements KeyListener{

	private Snake snake;

	public void keyPressed(KeyEvent ev) {
		//keyPressed events are for catching events like function keys, enter, arrow keys
		//We want to listen for arrow keys to move snake
		//Has to id if user pressed arrow key, and if so, send info to Snake object

		//is game running? No? tell the game to draw grid, start, etc.
		
		//Get the component which generated this event
		//Hopefully, a DrawSnakeGamePanel object.

		char keyPressed = ev.getKeyChar(); // setting up the parameters for user gamespeed.
		char one = '1';
		char two = '2';
		char three = '3';


		DrawSnakeGamePanel panel = (DrawSnakeGamePanel)ev.getComponent();


		if (SnakeGame.getGameStage() == SnakeGame.BEFORE_GAME && keyPressed == one){
			//Start the game
			SnakeGame.setClockInterval(250);
			SnakeGame.setGameStage(SnakeGame.DURING_GAME);
			SnakeGame.newGame();
			panel.repaint();
			return;

		} else if (SnakeGame.getGameStage() == SnakeGame.BEFORE_GAME && keyPressed == two){
			//Start the game
			SnakeGame.setClockInterval(200);
			SnakeGame.setGameStage(SnakeGame.DURING_GAME);
			SnakeGame.newGame();
			panel.repaint();
			return;
		}
		else if (SnakeGame.getGameStage() == SnakeGame.BEFORE_GAME && keyPressed == three){
			//Start the game
			SnakeGame.setClockInterval(150);
			SnakeGame.setGameStage(SnakeGame.DURING_GAME);
			SnakeGame.newGame();
			panel.repaint();
			return;
		}

		if (SnakeGame.getGameStage() == SnakeGame.GAME_OVER){
			Score.resetScore();
			//Need to start the timer and start the game again
			SnakeGame.newGame();
			SnakeGame.setGameStage(SnakeGame.DURING_GAME);
			panel.repaint();
			return;
		}

	}


	@Override
	public void keyReleased(KeyEvent ev) {
		//We don't care about keyReleased events, but are required to implement this method anyway.		
	}


	@Override
	public void keyTyped(KeyEvent ev) {
		//keyTyped events are for user typing letters on the keyboard, anything that makes a character display on the screen
		char keyPressed = ev.getKeyChar();
		char q = 'q';
		char m = 'm';
		char w = 'w';


		if( keyPressed == q){
			System.exit(0);    //quit if user presses the q key.
		}
		if (keyPressed == m) { // turns walls on and off.

			DrawSnakeGamePanel panel = (DrawSnakeGamePanel)ev.getComponent();

			if (panel.isWalls == false) {

				panel.isWalls = true;

			} else {

				panel.isWalls = false;

			}

		}
		if (keyPressed == w) {

			DrawSnakeGamePanel panel = (DrawSnakeGamePanel)ev.getComponent();


			if (panel.snake.wrap == false) {

				panel.snake.wrap = true;
			}

		}

	}

}

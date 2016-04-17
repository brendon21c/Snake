package com.Brendon;

/** Keeps track of, and display the user's score
 * 
 */


public class Score {

	protected static int score;
	protected static int highScore = 0;
	protected static int increment;
	protected Snake snake;



	public Score(){

		score = 0;
		increment = 1;  //how many points for eating a kibble

		/*
		int currentSnakeSize = snake.getSnakeSize();


		if (currentSnakeSize == 5) {

			increment = 2;
		} else if (currentSnakeSize == 10) {

			increment = 3;
		} else if (currentSnakeSize >= 15) {

			increment = 5;
		} */
	}
	
	public static void resetScore() {
		score = 0;	
	}
	
	public static void increaseScore() {

		long speed = SnakeGame.getClockInterval();


		/*
		This block adjusts how many points the player gets, the longer the snake and the speed of
		the game the more points they receive.
		 */

		if (speed == 250) { // easy mode

			if (score < 5) {

				increment = 1;
				score = score + increment;

			} else if (score >= 5 & score < 10) {

				increment = 2;
				score = score + increment;

			} else if (score >= 10 & score < 15) {

				increment = 3;
				score = score + increment;

			} else if (score >= 15 & score < 20) {

				increment = 4;
				score = score + increment;

			} else {

				increment = 5;
				score = score + increment;

			}
		}

		if (speed == 200) { // medium mode

			if (score < 5) {

				increment = 1;
				score = score + increment;

			} else if (score >= 5 & score < 10) {

				increment = 3;
				score = score + increment;

			} else if (score >= 10 & score < 15) {

				increment = 5;
				score = score + increment;

			} else if (score >= 15 & score < 20) {

				increment = 7;
				score = score + increment;

			} else {

				increment = 9;
				score = score + increment;

			}
		}

		if (speed == 150) { // hard mode

			if (score < 5) {

				increment = 1;
				score = score + increment;

			} else if (score >= 5 & score < 10) {

				increment = 3;
				score = score + increment;

			} else if (score >= 10 & score < 15) {

				increment = 6;
				score = score + increment;

			} else if (score >= 15 & score < 20) {

				increment = 9;
				score = score + increment;

			} else {

				increment = 12;
				score = score + increment;

			}
		}
		

	}
	
	public int getScore(){
		return score;
	}
	
	//Checks if current score is greater than the current high score. 
	//updates high score and returns true if so.
	
	public boolean gameOver(){
		
		if (score > highScore) {
			highScore = score;
			return true;
		}
		return false;
	}

	//These methods are useful for displaying score at the end of the game
	
	public String getStringScore() {
		return Integer.toString(score);
	}

	public String newHighScore() {
		
		if (score > highScore) {
			highScore = score;
			return "New High Score!!";
		} else {
			return "";
	}
	}

	public String getStringHighScore() {
		return Integer.toString(highScore);
	}
	
}


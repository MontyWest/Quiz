package runner;

import domain.Quiz;

public class QuizPlayRunner {

	private Quiz quiz;
	private int scoreAmount;
	
	public QuizPlayRunner(Quiz quiz) {
		this.quiz = quiz;
		this.scoreAmount = 0;
	}
	
	public void start() {
		//TODO
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	public int getScoreAmount() {
		return scoreAmount;
	}
	
	
}

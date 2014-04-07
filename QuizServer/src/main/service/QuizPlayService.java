package service;

import domain.Quiz;
import domain.Score;

public interface QuizPlayService {

	public String getQuizListDisplay();
	
	public Quiz getQuiz(Long quizId);
	
	public boolean updateQuizWithScore(Long quizId, Score score);
	
}

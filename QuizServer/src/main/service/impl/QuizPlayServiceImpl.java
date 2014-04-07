package service.impl;

import service.QuizPlayService;
import domain.Quiz;
import domain.QuizList;
import domain.Score;
import domain.impl.QuizListImpl;

public class QuizPlayServiceImpl implements QuizPlayService {

	@Override
	public String getQuizListDisplay() {
		QuizList quizList = QuizListImpl.getInstance();
		return quizList.toString();
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		QuizList quizList = QuizListImpl.getInstance();
		Quiz quiz = quizList.getQuiz(quizId);
		if (quiz == null) {
			//TODO: Exception?
		}
		if (!quiz.isValid()) {
			//TODO: Exception?
		}
		return quiz;
	}

	@Override
	public boolean updateQuizWithScore(Long quizId, Score score) {
		QuizList quizList = QuizListImpl.getInstance();
		Quiz quiz = quizList.getQuiz(quizId);
		if (quiz == null) {
			//TODO: Exception?
		}
		return quiz.compareAndSetTopScore(score);
	}

}

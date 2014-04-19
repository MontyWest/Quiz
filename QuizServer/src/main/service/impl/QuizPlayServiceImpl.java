package service.impl;

import java.util.HashSet;
import java.util.Set;

import service.QuizPlayService;
import domain.Quiz;
import domain.QuizList;
import domain.Score;
import domain.impl.QuizListImpl;
import exception.QuizInvalidException;
import exception.QuizNotFoundException;
import factory.ScoreFactory;
import factory.impl.ScoreFactoryImpl;

public class QuizPlayServiceImpl implements QuizPlayService {

	ScoreFactory scoreFactory = new ScoreFactoryImpl();
	
	@Override
	public String getQuizListDisplay() {
		QuizList quizList = QuizListImpl.getInstance();
		return quizList.display();
	}
	
	@Override
	public Set<Long> getQuizIdSet() {
		QuizList quizList = QuizListImpl.getInstance();
		return new HashSet<Long>(quizList.getQuizzes().keySet());
	}

	@Override
	public Quiz getQuiz(Long quizId) throws QuizInvalidException {
		QuizList quizList = QuizListImpl.getInstance();
		Quiz quiz = quizList.getQuiz(quizId);
		if (quiz == null) {
			return null;
		}
		if (!quiz.isValid()) {
			throw new QuizInvalidException("Invalid quiz - ID: " + quizId);
		}
		return quiz;
	}

	@Override
	public boolean updateQuizWithScore(Long quizId, int scoreAmount, String scoreName) throws QuizInvalidException, QuizNotFoundException, IllegalArgumentException {
		QuizList quizList = QuizListImpl.getInstance();
		Quiz quiz = quizList.getQuiz(quizId);
		if (quiz == null) {
			throw new QuizNotFoundException("Can't find quiz - ID: " + quizId);
		}
		if (!quiz.isValid()) {
			throw new QuizInvalidException("Invalid quiz - ID: " + quizId);
		}
		if (scoreAmount > quiz.getMaxScore()) {
			throw new IllegalArgumentException("Score " + scoreAmount + " too high for quiz " + quizId);
		}
		Score score = scoreFactory.getEmptyScore();
		score.setAmount(scoreAmount);
		score.setName(scoreName);
		score.setQuizId(quizId);
		return quiz.compareAndSetTopScore(score);
	}

}

package service.impl;

import service.QuizSetUpService;
import domain.Quiz;
import domain.QuizList;
import domain.impl.QuizListImpl;
import exception.QuizInvalidException;
import factory.QuizFactory;
import factory.impl.QuizFactoryImpl;

public class QuizSetUpServiceImpl implements QuizSetUpService {

	QuizFactory quizFactory = new QuizFactoryImpl();
	
	@Override
	public QuizFactory getQuizFactory() {
		return quizFactory;
	}

	@Override
	public Long addQuiz(Quiz quiz) throws QuizInvalidException {
		if (!quiz.isValid()) {
			throw new QuizInvalidException();
		}
		QuizList quizList = QuizListImpl.getInstance();
		return quizList.addQuiz(quiz);
	}

	@Override
	public Quiz removeQuiz(Long quizId) {
		QuizList quizList = QuizListImpl.getInstance();
		Quiz closedQuiz = quizList.removeQuiz(quizId);
		return closedQuiz;
	}

}

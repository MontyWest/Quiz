package service.impl;

import service.QuizSetUpService;
import domain.Quiz;
import domain.QuizList;
import domain.impl.QuizListImpl;
import factory.QuizFactory;
import factory.impl.QuizFactoryImpl;

public class QuizSetUpServiceImpl implements QuizSetUpService {

	@Override
	public QuizFactory getQuizFactory() {
		return new QuizFactoryImpl();
	}

	@Override
	public Long addQuiz(Quiz quiz) {
		if (quiz.isValid()) {
			QuizList quizList = QuizListImpl.getInstance();
			return quizList.addQuiz(quiz);
		} else {
			//TODO: Exception?
			return 0l;
		}
	}

	@Override
	public Quiz closeQuiz(Long quizId) {
		QuizList quizList = QuizListImpl.getInstance();
		Quiz closedQuiz = quizList.removeQuiz(quizId);
		if (closedQuiz == null) {
			//TODO: Exception?
		}
		return closedQuiz;
	}

}

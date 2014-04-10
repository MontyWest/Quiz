package factory.impl;

import java.io.Serializable;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import domain.impl.PossibleAnswerImpl;
import domain.impl.QuestionImpl;
import domain.impl.QuizImpl;
import factory.QuizFactory;

public class QuizFactoryImpl implements QuizFactory, Serializable {

	private static final long serialVersionUID = 11L;

	@Override
	public Quiz getEmptyQuiz() {
		return new QuizImpl();
	}

	@Override
	public Question getEmptyQuestion() {
		return new QuestionImpl();
	}

	@Override
	public PossibleAnswer getEmptyPossibleAnswer() {
		return new PossibleAnswerImpl();
	}
}

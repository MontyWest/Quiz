package factory.impl;

import java.io.Serializable;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import domain.Score;
import domain.impl.PossibleAnswerImpl;
import domain.impl.QuestionImpl;
import domain.impl.QuizImpl;
import domain.impl.ScoreImpl;
import factory.QuizFactory;

public class QuizFactoryImpl implements QuizFactory, Serializable {

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

	@Override
	public Score getEmptyScore() {
		return new ScoreImpl();
	}

}

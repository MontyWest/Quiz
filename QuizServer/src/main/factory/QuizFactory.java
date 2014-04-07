package factory;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import domain.Score;

public interface QuizFactory {

	public Quiz getEmptyQuiz();
	
	public Question getEmptyQuestion();
	
	public PossibleAnswer getEmptyPossibleAnswer();
	
	public Score getEmptyScore();
}

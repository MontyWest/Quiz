package factory;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;

public interface QuizFactory {

	/***
	 * Gets an empty quiz, with no questions, score or id
	 * 
	 * @return empty quiz
	 */
	public Quiz getEmptyQuiz();
	
	/***
	 * Gets and Empty question with no question number or
	 * possible answers
	 * 
	 * @return empty question
	 */
	public Question getEmptyQuestion();
	
	/***
	 * Gets and empty possible answer, with no answer character.
	 * 
	 * @return empty possible answer
	 */
	public PossibleAnswer getEmptyPossibleAnswer();
}

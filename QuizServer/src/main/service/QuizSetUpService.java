package service;

import domain.Quiz;
import factory.QuizFactory;

/***
 * Houses logic for requests made to server from
 * set-up client, pertaining to the creation and
 * closure of quizzes.
 * 
 * @author montywest
 *
 */
public interface QuizSetUpService {

	/***
	 * Gets a new quiz factory.
	 * 
	 * @return
	 */
	public QuizFactory getQuizFactory();
	
	/***
	 * Adds a Quiz to the QuizList
	 * 
	 * @param quiz
	 * @return id of new quiz
	 */
	public Long addQuiz(Quiz quiz);
	
	/***
	 * Closes quiz corresponding to id
	 * 
	 * @param quizId
	 * @return closed quiz, null if not found
	 */
	public Quiz closeQuiz(Long quizId);
}

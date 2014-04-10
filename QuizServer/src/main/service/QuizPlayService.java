package service;

import java.util.Set;

import domain.Quiz;
import exception.QuizInvalidException;
import exception.QuizNotFoundException;

/***
 * Houses logic for requests made to server from
 * play client, pertaining to the view and
 * playing of quizzes.
 * 
 * @author montywest
 *
 */
public interface QuizPlayService {

	/***
	 * Gets a string representing the list of quizzes,
	 * with id and title of each.
	 * 
	 * @return
	 */
	public String getQuizListDisplay();
	
	/***
	 * Get list of ids of available quizzes
	 * 
	 * @return
	 */
	public Set<Long> getQuizIdSet();
	
	/***
	 * Gets the quiz corresponding to the id
	 * 
	 * @param quizId
	 * @return
	 */
	public Quiz getQuiz(Long quizId) throws QuizInvalidException;
	
	/***
	 * Checks to see if score is the new high score for
	 * quiz corresponding to quiz id, adds it as top score if so
	 * 
	 * @param quizId
	 * @param score
	 * @return true if highest score false otherwise
	 */
	public boolean updateQuizWithScore(Long quizId, int scoreAmount, String scoreName) throws QuizInvalidException, QuizNotFoundException, IllegalArgumentException;
	
}

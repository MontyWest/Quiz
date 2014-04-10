package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import domain.Quiz;
import domain.Score;
import exception.QuizInvalidException;
import exception.QuizNotFoundException;
import factory.QuizFactory;

public interface QuizServerController extends Remote {
	
	/***
	 * Gets the display of the current quizzes available with id
	 * and title for each.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getQuizListDisplay() throws RemoteException;
	
	/***
	 * Gets list of ids of available quizzes
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public Set<Long> getQuizIds() throws RemoteException;
	
	/***
	 * Gets quiz corresponding to the id, returns null if not found
	 * 
	 * @param quizId
	 * @return
	 * @throws RemoteException
	 * @throws QuizInvalidException if quiz is invalid
	 */
	public Quiz getQuiz(Long quizId) throws RemoteException, QuizInvalidException;
	
	/***
	 * Recieves a score and name for quiz corresponding to id, calculates new top score
	 * for that quiz 
	 * 
	 * @param quizId
	 * @param scoreAmount
	 * @param scoreName
	 * @return true is score is now top score for quiz
	 * @throws RemoteException
	 * @throws QuizInvalidException if quiz is invalid
	 * @throws QuizNotFoundException if quiz can't be found
	 */
	public boolean recieveScoreForQuiz(Long quizId, int scoreAmount, String scoreName) throws RemoteException, QuizInvalidException, QuizNotFoundException, IllegalArgumentException;
	
	
	/***
	 * Gets a new quiz factory
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public QuizFactory getQuizFactory() throws RemoteException;
	
	/***
	 * Used to submit a new quiz to the server, adds it to the
	 * quiz list if it's valid.
	 * 
	 * @param quiz
	 * @return id for the newly added quiz
	 * @throws RemoteException
	 * @throws QuizInvalidException if quiz is invalid
	 */
	public Long recieveNewQuiz(Quiz quiz) throws RemoteException, QuizInvalidException, IllegalArgumentException;
	
	/***
	 * Closes the quiz corresponding to the id, returns the score of
	 * the closed quiz.
	 * 
	 * @param quizId
	 * @return
	 * @throws RemoteException
	 * @throws QuizNotFoundException if quiz doesn't exist
	 */
	public Score closeQuiz(Long quizId) throws RemoteException, QuizNotFoundException;
}

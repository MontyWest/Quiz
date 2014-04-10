package domain;

import java.util.Map;

public interface QuizList {
	
	public Map<Long, Quiz> getQuizzes();
	
	public void setQuizzes(Map<Long, Quiz> quizzes);
	
	
	/***
	 * Returns the quiz corresponding to the quizId
	 * 
	 * @param quizId
	 * @return the quiz with quizId
	 */
	public Quiz getQuiz(Long quizId);
	
	/***
	 * Adds a new quiz
	 * 
	 * @param quiz
	 * @return the id of the new quiz
	 */
	public Long addQuiz(Quiz quiz);
	
	/***
	 * Removes quiz corresponding to quizId
	 * 
	 * @param quizId
	 * @return the removed quiz, null if quizId didn't exist
	 */
	public Quiz removeQuiz(Long quizId);
	
}

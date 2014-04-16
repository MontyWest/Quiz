package domain;

import java.util.Map;

public interface Quiz {

	public Long getId();
	
	public String getTitle();
	
	public Map<Integer, Question> getQuestions();
	
	/***
	 * Returns the question in the questions map corresponding to the
	 * questionNumber
	 * 
	 * @param questionNumber
	 */
	public Question getQuestion(Integer questionNumber);
	
	public int getMaxScore();
	
	public Score getTopScore();
	
	/***
	 * Validates the quiz by checking it has questions,
	 * that those questions have answers and has at least
	 * one correct answer.
	 * 
	 * @return false if quiz is not valid
	 */
	public boolean isValid();
}

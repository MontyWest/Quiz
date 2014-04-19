package domain;

import java.util.Map;

public interface Quiz {
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public Map<Integer, Question> getQuestions();
	
	public void setQuestions(Map<Integer, Question> questions);
	
	/***
	 * Returns the question in the questions map corresponding to the
	 * questionNumber
	 * 
	 * @param questionNumber
	 */
	public Question getQuestion(Integer questionNumber);
	
	/***
	 * Adds question to the quiz, assigning the correct question number
	 * and updating the maxScore
	 * 
	 * @param question
	 * @return the question number of added question
	 */
	public Integer addQuestion(Question question);
	
	public int getMaxScore();
	
	public void setMaxScore(int maxScore);
	
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

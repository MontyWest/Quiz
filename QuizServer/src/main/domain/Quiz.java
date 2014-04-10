package domain;

import java.util.Map;

public interface Quiz {

	public Long getId();
	
	public void setId(Long id);
	
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
	 * Adds question to the quiz
	 * 
	 * @param question
	 * @return the question number of added question
	 */
	public Integer addQuestion(Question question);
	
	public Score getTopScore();
	
	public void setTopScore(Score topScore);
	
	/***
	 * Compares newScore to current top Score, the top Score
	 * becomes newScore is newScore > topScore
	 * 
	 * @param newScore
	 * @returns true if topScore changed
	 */
	public boolean compareAndSetTopScore(Score newScore);
	
	/***
	 * Validates the quiz by checking it has questions,
	 * that those questions have answers and has at least
	 * one correct answer.
	 * 
	 * @return false if quiz is not valid
	 */
	public boolean isValid();
}

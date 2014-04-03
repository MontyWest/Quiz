package domain;

import java.util.Map;

public interface Quiz {

	public Long getId();
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public Map<Integer, Question> getQuestions();
	
	public void setQuestions(Map<Integer, Question> questions);
	
	/***
	 * Copies a question and adds it to the quiz.
	 * 
	 * @param question
	 */
	public Question copyAndAddQuestion(Question question);
	
	/***
	 * Creates and adds a new empty (answerless) question to the quiz
	 * 
	 * @param questionText
	 * @return created question
	 */
	public Question addQuestion(String questionText);
	
	/***
	 * Removes the question with the corresponding questionNumber
	 * 
	 * @param quesitonNumber
	 * @return true is successful, false if questionNumber doesn't exist
	 */
	public boolean removeQuestion(Integer quesitonNumber);
	
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
	 * Creates new score object then compares new Score to current 
	 * top Score, the top Score becomes new Score is new Score > topScore
	 * 
	 * @param amount
	 * @param name
	 * @return true if topScore changed
	 */
	public boolean compareAndSetTopScore(int amount, String name);
	
	/***
	 * Validates the quiz by checking it has questions,
	 * that those questions have answers and has at least
	 * one correct answer.
	 * 
	 * @return false if quiz is not valid
	 */
	public boolean isValid();
}

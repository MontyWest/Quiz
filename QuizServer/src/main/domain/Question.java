package domain;

import java.util.Map;

public interface Question {
	
	public String getQuestionText();
	
	public void setQuestionText(String questionText);

	public Map<Character, PossibleAnswer> getPossibleAnswers();
	
	public void setPossibleAnswers(Map<Character, PossibleAnswer> possibleAnswers);

	/***
	 * Returns the possible answer corresponding to the answer character from the 
	 * answers map
	 * 
	 * @param answerCharacter
	 * @return
	 */
	public PossibleAnswer getPossibleAnswer(Character answerCharacter);
	
	/***
	 * Adds a new possible answer to the question
	 * 
	 * @param possibleAnswer
	 * @return character of the possible answer
	 */
	public Character addPossibleAnswer(PossibleAnswer possibleAnswer);
	
	public Long getQuizId();
	
	public void setQuizId(Long quizId);
	
	public Integer getQuestionNumber();
	
	public void setQuestionNumber(Integer questionNumber);
	
	/***
	 * Validates the question by checking that question text is
	 * not null or empty, if question has answers, that they are valid and that
	 * at least one is correct.
	 * 
	 * @return false if question is not valid
	 */
	public boolean isValid();
	
}

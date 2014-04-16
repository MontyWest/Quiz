package domain;

import java.util.Map;

public interface Question {
	
	public String getQuestionText();
	
	public Map<Character, PossibleAnswer> getPossibleAnswers();
	
	/***
	 * Returns the possible answer corresponding to the answer character from the 
	 * answers map
	 * 
	 * @param answerCharacter
	 * @return
	 */
	public PossibleAnswer getPossibleAnswer(Character answerCharacter);
	
	public Long getQuizId();
	
	public Integer getQuestionNumber();
	
	/***
	 * Validates the question by checking that question text is
	 * not null or empty, if question has answers, that they are valid and that
	 * at least one is correct.
	 * 
	 * @return false if question is not valid
	 */
	public boolean isValid();
	
}

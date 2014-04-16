package domain;

public interface PossibleAnswer {
	
	public String getAnswerText();
		
	public boolean isCorrect();
		
	public Integer getQuestionNumber();
		
	public Long getQuizId();
		
	public Character getAnswerCharacter();
		
	/***
	 * Checks that answerText is not null or empty and
	 * if answerCharacter is not null
	 * 
	 * @return true if valid
	 */
	public boolean isValid();
	
}

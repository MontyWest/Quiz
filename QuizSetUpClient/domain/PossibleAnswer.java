package domain;

public interface PossibleAnswer {
	
	public String getAnswerText();
	
	public void setAnswerText(String answerText);
	
	public boolean isCorrect();
	
	public void setCorrect(boolean correct);
	
	public Integer getQuestionNumber();

	public Character getAnswerCharacter();
	
	/***
	 * Checks that answerText is not null or empty and
	 * if answerCharacter is not null
	 * 
	 * @return true if valid
	 */
	public boolean isValid();
	
}

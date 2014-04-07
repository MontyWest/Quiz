package domain;

public interface PossibleAnswer {
	
	public String getAnswerText();
	
	public void setAnswerText(String answerText);
	
	public boolean isCorrect();
	
	public void setCorrect(boolean correct);
	
	public Integer getQuestionNumber();
	
	public void setQuestionNumber(Integer quesitonNumber);
	
	public Long getQuizId();
	
	public void setQuizId(Long quizId);
	
	public Character getAnswerCharacter();
	
	public void setAnswerCharacter(Character answerCharacter);
	
	/***
	 * Checks that answerText is not null or empty and
	 * if answerCharacter is not null
	 * 
	 * @return true if valid
	 */
	public boolean isValid();
	
}

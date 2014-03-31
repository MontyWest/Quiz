package model;

public interface PossibleAnswer {
	
	public String getAnswerText();
	
	public void setAnswerText(String answerText);
	
	public boolean isCorrect();
	
	public void setCorrect(boolean correct);
	
	public Long getQuestionId();
	
	public void setQuestionId(Long quesitonId);
	
}

package model;

import java.util.Map;

public interface Question {

	public Map<Character, PossibleAnswer> getPossibleAnswers();
	
	public void addPossibleAnswer(PossibleAnswer answer);
	
	public Long getQuizId();
	
	public void setQuizId(Long quizId);
	
}

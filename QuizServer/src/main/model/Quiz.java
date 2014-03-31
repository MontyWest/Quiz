package model;

import java.util.Map;

public interface Quiz {

	public Long getId();
	
	public Map<Integer, Question> getQuestions();
	
	public void addQuestion(Question question);
	
	public Score getTopScore();
	
	public void setTopScore();
	
}

package domain;

public interface Score extends Comparable<Score> {

	public int getAmount();
	
	public void setAmount(int amount);
	
	public String getName();
	
	public void setName(String name);
	
	public Long getQuizId();
	
	public void setQuizId(Long quizId);
	
}

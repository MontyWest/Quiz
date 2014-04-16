package domain;

public interface Score extends Comparable<Score> {

	public int getAmount();
		
	public String getName();	
	
	public Long getQuizId();	
}

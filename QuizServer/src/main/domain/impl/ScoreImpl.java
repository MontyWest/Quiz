package domain.impl;

import java.io.Serializable;

import domain.Score;

public class ScoreImpl implements Score, Serializable {

	private static final long serialVersionUID = 2L;
	
	private int amount;
	private String name;
	private Long quizId;
	
	public ScoreImpl() {}
	
	public ScoreImpl(int amount, String name, Long quizId) {
		this.amount = amount;
		this.name = name;
		this.quizId = quizId;
	}
	
	@Override
	public int getAmount() {
		return amount;
	}
	
	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Long getQuizId() {
		return quizId;
	}
	
	@Override
	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}
	
	@Override
	public int compareTo(Score score) {
		return this.getAmount() - score.getAmount();
	}
	
	@Override
	public String toString() {
		return (this.amount + " by " + this.name);
	}
}

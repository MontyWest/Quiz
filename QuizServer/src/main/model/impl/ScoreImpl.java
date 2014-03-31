package model.impl;

import model.Score;

public class ScoreImpl implements Score {

	private int amount;
	private String name;
	private Long quizId;
	
	public ScoreImpl(){}
	
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
	
	
}

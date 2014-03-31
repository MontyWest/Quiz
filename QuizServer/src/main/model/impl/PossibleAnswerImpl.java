package model.impl;

import model.PossibleAnswer;

public class PossibleAnswerImpl implements PossibleAnswer {

	private String answerText;
	private boolean correct;
	private Long questionId;
	
	public PossibleAnswerImpl(){}
	
	public PossibleAnswerImpl(String answerText, boolean correct,
			Long questionId) {
		super();
		this.answerText = answerText;
		this.correct = correct;
		this.questionId = questionId;
	}
	
	@Override
	public String getAnswerText() {
		return answerText;
	}
	
	@Override
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	@Override
	public boolean isCorrect() {
		return correct;
	}
	
	@Override
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	@Override
	public Long getQuestionId() {
		return questionId;
	}
	
	@Override
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

}

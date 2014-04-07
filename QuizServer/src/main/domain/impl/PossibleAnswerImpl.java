package domain.impl;

import java.io.Serializable;

import domain.PossibleAnswer;

public class PossibleAnswerImpl implements PossibleAnswer, Serializable {

	private static final long serialVersionUID = 1L;

	private String answerText;
	private boolean correct;
	private Integer questionNumber;
	private Long quizId;
	private Character answerCharacter;

	public PossibleAnswerImpl() {
	}

	public PossibleAnswerImpl(String answerText, boolean correct,
			Character answerCharacter, Integer questionNumber, Long quizId) {
		this.answerText = answerText;
		this.correct = correct;
		this.answerCharacter = answerCharacter;
		this.questionNumber = questionNumber;
		this.quizId = quizId;
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
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	@Override
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
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
	public Character getAnswerCharacter() {
		return answerCharacter;
	}

	@Override
	public void setAnswerCharacter(Character answerCharacter) {
		this.answerCharacter = answerCharacter;
	}

	@Override
	public String toString() {
		return this.answerCharacter + ") " + this.answerText;
	}

	@Override
	public boolean isValid() {
		if (answerText == null || answerText.isEmpty()) {
			return false;
		}
		return true;
	}
}

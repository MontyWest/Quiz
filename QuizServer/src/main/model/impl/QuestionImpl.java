package model.impl;

import java.util.HashMap;
import java.util.Map;

import model.PossibleAnswer;
import model.Question;

public class QuestionImpl implements Question {

	private Map<Character, PossibleAnswer> possibleAnswers;
	private Long quizId;
	
	public QuestionImpl(){}

	public QuestionImpl(Long quizId) {
		this.quizId = quizId;
		this.possibleAnswers = new HashMap<Character, PossibleAnswer>();
	}
	
	@Override
	public Map<Character, PossibleAnswer> getPossibleAnswers() {
		return possibleAnswers;
	}
	
	@Override
	public void addPossibleAnswer(PossibleAnswer answer) {
		Character answerChar = (char)(((int)'a') + possibleAnswers.size());
		possibleAnswers.put(answerChar, answer);
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

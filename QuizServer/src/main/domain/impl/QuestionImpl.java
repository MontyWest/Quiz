package domain.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import domain.PossibleAnswer;
import domain.Question;

public class QuestionImpl implements Question, Serializable {

	private static final long serialVersionUID = 3L;
	
	private String questionText;
	private Map<Character, PossibleAnswer> possibleAnswers;
	private Long quizId;
	private Integer questionNumber;
	private AtomicInteger lastAnswerCharInt;
	
	public QuestionImpl(){}

	public QuestionImpl(String questionText, Integer questionNumber, Long quizId) {
		this.questionText = questionText;
		this.quizId = quizId;
		this.possibleAnswers = new HashMap<Character, PossibleAnswer>();
		this.lastAnswerCharInt.set(((int)'a')-1);
	}
	
	@Override
	public String getQuestionText() {
		return questionText;
	}

	@Override
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@Override
	public Map<Character, PossibleAnswer> getPossibleAnswers() {
		return Collections.unmodifiableMap(possibleAnswers);
	}
	
	@Override
	public void setPossibleAnswers(Map<Character, PossibleAnswer> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	@Override
	public PossibleAnswer copyAndAddPossibleAnswer(PossibleAnswer answer) {
		Character newAnswerChar = (char)(this.lastAnswerCharInt.incrementAndGet());
		PossibleAnswer newAnswer = answer.copy(this.quizId, this.questionNumber, newAnswerChar);
		possibleAnswers.put(newAnswerChar, newAnswer);
		return newAnswer;
	}
	
	@Override
	public PossibleAnswer addPossibleAnswer(String answerText, boolean correct) {
		Character answerChar = (char)(this.lastAnswerCharInt.incrementAndGet());
		PossibleAnswer possibleAnswer = new PossibleAnswerImpl(answerText, correct, answerChar, this.questionNumber, this.quizId);
		possibleAnswers.put(answerChar, possibleAnswer);
		return possibleAnswer;
	}
	
	@Override
	public boolean removePossibleAnswer(Character answerChar){
		if (this.possibleAnswers != null) {
			if (possibleAnswers.containsKey(answerChar)) {
				possibleAnswers.remove(answerChar);
				//TODO sort out question numbers.
				return true;
			}
		}
		return false;
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
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	@Override
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	@Override
	public boolean isValid() {
		int correctAnswerCount = 0;
		if(possibleAnswers == null || possibleAnswers.isEmpty()) {
			return false;
		} else if(questionText == null || questionText.isEmpty()) {
			return false;
		} else {
			for(PossibleAnswer answer : possibleAnswers.values()) {
				if(answer == null || !answer.isValid()) {
					return false;
				} else if (answer.isCorrect()) {
					correctAnswerCount++;
				}
			}
		}
		return (correctAnswerCount == 1);
	}
	
	@Override
	public Question copy(Long newQuizId, Integer newQuestionNumber){
		Question newQuestion = new QuestionImpl(this.questionText, newQuestionNumber, newQuizId);
		for (PossibleAnswer answer : this.possibleAnswers.values()) {
			newQuestion.copyAndAddPossibleAnswer(answer);
		}
		return newQuestion;
	}
	
	@Override
	public String toString() {
		return this.questionNumber + ": " + this.questionText;
	}
	
}

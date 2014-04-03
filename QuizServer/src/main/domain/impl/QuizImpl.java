package domain.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import domain.Question;
import domain.Quiz;
import domain.Score;

public class QuizImpl implements Quiz, Serializable {
	
	private static final long serialVersionUID = 4L;
	
	private final Long id;
	private String title;
	private Map<Integer, Question> questions;
	private Score topScore;
	private AtomicInteger lastQuestionNumber;
	
	public QuizImpl(Long id) {
		this.id = id;
		
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Map<Integer, Question> getQuestions() {
		return questions;
	}
	
	@Override
	public void setQuestions(Map<Integer, Question> questions) {
		this.questions = questions;
	}
	
	@Override
	public Question copyAndAddQuestion(Question question) {
		Integer newQuestionNumber = this.lastQuestionNumber.incrementAndGet();
		Question newQuestion = question.copy(this.id, newQuestionNumber);
		this.questions.put(newQuestionNumber, newQuestion);
		return newQuestion;
	}
	
	@Override
	public Question addQuestion(String questionText) {
		Integer questionNumber = this.lastQuestionNumber.incrementAndGet();
		Question question = new QuestionImpl(questionText, questionNumber, this.id);
		this.questions.put(questionNumber, question);
		return question;
	}
	
	@Override
	public boolean removeQuestion(final Integer questionNumber) {
		if (this.questions != null) {
			if (questions.containsKey(questionNumber)) {
				questions.remove(questionNumber);
				//TODO sort out question numbers.
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Score getTopScore() {
		return topScore;
	}
	
	@Override
	public void setTopScore(Score topScore) {
		this.topScore = topScore;
	}
	
	@Override
	public boolean compareAndSetTopScore(Score newScore) {
		if(this.topScore == null) {
			setTopScore(newScore);
			return true;
		} else {
			if(this.topScore.compareTo(newScore) < 0) {
				setTopScore(newScore);
				return true;
			} else {
				return false;
			}
		}
	}
	
	@Override
	public boolean compareAndSetTopScore(int amount, String name) {
		Score newScore = new ScoreImpl(amount, name, this.id);
		return compareAndSetTopScore(newScore);
	}
	
	@Override
	public boolean isValid() {
		if(questions == null || questions.size() == 0) {
			return false;
		} else if(title == null || title.isEmpty()) {
			return false;
		} else {
			for (Question question : questions.values()) {
				if (question == null || !question.isValid()) {
					return false;
				}
			}
		}
		return true;
	}
}

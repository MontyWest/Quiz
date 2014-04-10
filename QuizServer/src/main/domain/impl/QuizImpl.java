package domain.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import domain.Question;
import domain.Quiz;
import domain.Score;

public class QuizImpl implements Quiz, Serializable {
	
	private static final long serialVersionUID = 4L;
	
	private Long id;
	private String title;
	private Map<Integer, Question> questions;
	private int maxScore;
	private Score topScore;
	private AtomicInteger lastQuestionNumber;
	
	public QuizImpl() {}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
		for (Question question : questions.values()) {
			question.setQuizId(id);
		}
		if (topScore != null) {
			topScore.setQuizId(id);
		}
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
		return Collections.unmodifiableMap(questions);
	}
	
	@Override
	public void setQuestions(Map<Integer, Question> questions) {
		this.questions = questions;
		setMaxScore(questions.size());
	}
	
	@Override
	public Question getQuestion(Integer questionNumber) {
		return this.questions.get(questionNumber);
	}
	
	@Override
	public Integer addQuestion(Question question) {
		Integer questionNumber = this.lastQuestionNumber.incrementAndGet();
		question.setQuestionNumber(questionNumber);
		this.questions.put(questionNumber, question);
		setMaxScore(this.questions.size());
		return questionNumber;
	}
	
	@Override
	public int getMaxScore() {
		return maxScore;
	}
	
	@Override
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
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
	
	@Override
	public String toString() {
		return this.id + ": " + this.title;
	}
}

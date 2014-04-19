package domain.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import domain.Quiz;
import domain.QuizList;

public class QuizListImpl implements QuizList, Serializable {

	private static final long serialVersionUID = 5L;
	
	private static QuizList instance = new QuizListImpl();

	private Map<Long, Quiz> quizzes = new HashMap<Long, Quiz>();
	private AtomicLong lastQuizId = new AtomicLong();

	public static QuizList getInstance() {
		return instance;
	}

	private QuizListImpl() {}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		instance = this;
	}
	
	private Object readResolve() {
		return instance;
	}

	@Override
	public Map<Long, Quiz> getQuizzes() {
		return Collections.unmodifiableMap(quizzes);
	}

	@Override
	public void setQuizzes(Map<Long, Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		return quizzes.get(quizId);
	}

	@Override
	public Long addQuiz(Quiz quiz) {
		idCheck();
		Long quizId = lastQuizId.incrementAndGet();
		quiz.cascadeSetId(quizId);
		this.quizzes.put(quizId, quiz);
		return quizId;			
	}
	
	@Override
	public Quiz removeQuiz(Long quizId) {
		return this.quizzes.remove(quizId);
	}


	@Override
	public String display() {
		if (quizzes == null || quizzes.isEmpty()) {
			return ("No quizzes to display.");
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Long, Quiz> entry : quizzes.entrySet()) {
			sb.append(entry.getValue()).append("\n");
		}
		return sb.toString();
	}
	
	private void idCheck() {
		if(quizzes.containsKey(lastQuizId.get() + 1l)) {
			Set<Long> quizIds = quizzes.keySet();
			for (Long id : quizIds) {
				if (id > lastQuizId.get()) {
					lastQuizId.set(id);
				}
			}
		}
	}
	
}

package domain.impl;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Fixtures.Fixtures;
import domain.Quiz;
import domain.QuizList;

public class QuizListImplTest {
	
	private QuizList quizList;
	private Quiz exampleQuiz;
	
	@Before
	public void setUp() {
		exampleQuiz = Fixtures.getExampleQuizImpl();
		quizList = QuizListImpl.getInstance();
	}
	
	@Test
	public void testsGetQuiz() {
		Map<Long, Quiz> quizMap = new HashMap<Long, Quiz>();
		quizMap.put(exampleQuiz.getId(), exampleQuiz);
		quizList.setQuizzes(quizMap);
		Quiz returnedQuiz = quizList.getQuiz(100l);
		assertTrue(exampleQuiz == returnedQuiz);
	}
	
	@Test
	public void testAddQuiz() {
		Long newId = quizList.addQuiz(exampleQuiz);
		Quiz returnedQuiz = quizList.getQuizzes().get(newId);
		assertTrue(exampleQuiz == returnedQuiz);
	}
	
	@Test
	public void testAddQuizAssignsId() {
		exampleQuiz.setId(null);
		Long newId = quizList.addQuiz(exampleQuiz);
		assertTrue(newId != null);
		assertTrue(newId > 0l);
	}
	
	@Test
	public void testRemoveQuiz() {
		Map<Long, Quiz> quizMap = new HashMap<Long, Quiz>();
		quizMap.put(exampleQuiz.getId(), exampleQuiz);
		quizList.setQuizzes(quizMap);
		Quiz removedQuiz = quizList.removeQuiz(100l);
		Quiz returnedQuiz = quizList.getQuizzes().get(100l);
		assertTrue(exampleQuiz == removedQuiz);
		assertTrue(returnedQuiz == null);
	}
	
	@After
	public void cleanUp() {
		quizList.setQuizzes(new HashMap<Long, Quiz>());
	}
	
}

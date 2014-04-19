package domain.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import Fixtures.Fixtures;
import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import domain.Score;

public class QuizImplTest {

	private Quiz exampleQuiz;
	
	@Before
	public void setUp() {
		exampleQuiz = Fixtures.getExampleQuizImpl();
	}
	
	@Test
	public void testCascadeSetId() {
		exampleQuiz.cascadeSetId(50l);
		assertEquals(exampleQuiz.getId().longValue(), 50l);
		assertEquals(exampleQuiz.getQuestions().get(1).getQuizId().longValue(), 50l);
		assertEquals(exampleQuiz.getQuestions().get(2).getPossibleAnswers().get('b').getQuizId().longValue(), 50l);
	}
	
	@Test
	public void testGetQuestion() {
		Question returnedQuestion1 = exampleQuiz.getQuestion(1);
		Question returnedQuestion2 = exampleQuiz.getQuestion(2);
		Question returnedQuestion3 = exampleQuiz.getQuestion(3);
		Question expectedQuestion1 = exampleQuiz.getQuestions().get(1);
		Question expectedQuestion2 = exampleQuiz.getQuestions().get(2);
		Question expectedQuestion3 = exampleQuiz.getQuestions().get(3);
		
		assertEquals(returnedQuestion1, expectedQuestion1);
		assertEquals(returnedQuestion2, expectedQuestion2);
		assertEquals(returnedQuestion3, expectedQuestion3);
	}
	
	@Test
	public void testAddQuesiton() {
		int oldMaxScore = exampleQuiz.getMaxScore();
		Question newQuestion = Fixtures.getNewQuestionImpl("X or Y?", 4, 100l);
		PossibleAnswer newPosAns1 = Fixtures.getNewPossibleAnswerImpl("X", 'a', true, 4, 100l);
		PossibleAnswer newPosAns2 = Fixtures.getNewPossibleAnswerImpl("Y", 'b', false, 4, 100l);
		Map<Character, PossibleAnswer> ansMap = new HashMap<Character, PossibleAnswer>();
		ansMap.put('a', newPosAns1);
		ansMap.put('b', newPosAns2);
		newQuestion.setPossibleAnswers(ansMap);
		exampleQuiz.addQuestion(newQuestion);
		Map<Integer, Question> returnedQuestions = exampleQuiz.getQuestions();
		assertTrue(returnedQuestions.containsValue(newQuestion));
		assertEquals(returnedQuestions.get(4), newQuestion);
		assertEquals(oldMaxScore + 1, exampleQuiz.getMaxScore());
	}
	
	@Test
	public void testCompareAndSetTopScoreSuccess() {
		Score newScore = Fixtures.getNewScoreImpl(3, "Newton", 100l);
		boolean result = exampleQuiz.compareAndSetTopScore(newScore);
		assertTrue(result);
		assertEquals(exampleQuiz.getTopScore(), newScore);
	}
	
	@Test
	public void testCompareAndSetTopScoreFail() {
		Score newScore = Fixtures.getNewScoreImpl(1, "Baddo", 100l);
		boolean result = exampleQuiz.compareAndSetTopScore(newScore);
		assertFalse(result);
		assertTrue(exampleQuiz.getTopScore() != newScore);
	}
	
	@Test
	public void testIsValidSuccess() {
		assertTrue(exampleQuiz.isValid());
	}
	
	@Test
	public void testIsValidTitleFail() {
		exampleQuiz.setTitle(null);
		assertFalse(exampleQuiz.isValid());
		exampleQuiz.setTitle("");
		assertFalse(exampleQuiz.isValid());
	}
	
	@Test
	public void testIsValidQuestionsFail() {
		exampleQuiz.setQuestions(null);
		assertFalse(exampleQuiz.isValid());
		exampleQuiz.setQuestions(Collections.<Integer, Question>emptyMap());
		assertFalse(exampleQuiz.isValid());
	}
	
	@Test
	public void testIsValidAnswersFail() {
		Question question = exampleQuiz.getQuestions().get(1);
		question.setPossibleAnswers(Collections.<Character, PossibleAnswer>emptyMap());
		assertFalse(exampleQuiz.isValid());
	}
	
	@Test
	public void testIsValidCorrectAnswersFail() {
		Question question = exampleQuiz.getQuestions().get(1);
		Map<Character, PossibleAnswer> posAnsMap = new HashMap<Character, PossibleAnswer>();
		posAnsMap.put('a', Fixtures.getNewPossibleAnswerImpl("A", 'a', false, 1, 100l));
		question.setPossibleAnswers(posAnsMap);
		assertFalse(exampleQuiz.isValid());
	}
}

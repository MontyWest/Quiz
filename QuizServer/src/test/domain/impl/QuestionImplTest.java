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


public class QuestionImplTest {

	private Question exampleQuestion;
	
	@Before
	public void setUp() {
		exampleQuestion = Fixtures.getNewQuestionImpl("Test?", 1, 100l);
		Map<Character, PossibleAnswer> posAnsMap = new HashMap<Character, PossibleAnswer>();
		posAnsMap.put('a', Fixtures.getNewPossibleAnswerImpl("Yes", 'a', true, 1, 100l));
		posAnsMap.put('b', Fixtures.getNewPossibleAnswerImpl("No", 'b', false, 1, 100l));
		exampleQuestion.setPossibleAnswers(posAnsMap);
	}
	
	@Test
	public void testCascadeSetQuizId() {
		exampleQuestion.cascadeSetQuizId(50l);
		assertEquals(exampleQuestion.getQuizId().longValue(), 50l);
		assertEquals(exampleQuestion.getPossibleAnswers().get('b').getQuizId().longValue(), 50l);
	}
	
	@Test
	public void testCascadeSetQuestionNumber() {
		exampleQuestion.cascadeSetQuestionNumber(2);
		assertEquals(exampleQuestion.getQuestionNumber().intValue(), 2);
		assertEquals(exampleQuestion.getPossibleAnswers().get('b').getQuestionNumber().intValue(), 2);
	}
	
	@Test
	public void testGetPossibleAnswer() {
		PossibleAnswer possibleAnswerA = exampleQuestion.getPossibleAnswer('a');
		PossibleAnswer possibleAnswerB = exampleQuestion.getPossibleAnswer('b');
		PossibleAnswer expectedPossibleAnswerA = exampleQuestion.getPossibleAnswers().get('a');
		PossibleAnswer expectedPossibleAnswerB = exampleQuestion.getPossibleAnswers().get('b');
		
		assertEquals(possibleAnswerA, expectedPossibleAnswerA);
		assertEquals(possibleAnswerB, expectedPossibleAnswerB);
	}
	
	@Test
	public void testAddPossibleAnswer() {
		PossibleAnswer newPosAnsC = Fixtures.getNewPossibleAnswerImpl("Maybe", 'c', false, 1, 100l);
		exampleQuestion.addPossibleAnswer(newPosAnsC);
		Map<Character, PossibleAnswer> returnedAnswers = exampleQuestion.getPossibleAnswers();
		assertTrue(returnedAnswers.containsValue(newPosAnsC));
		assertEquals(returnedAnswers.get('c'), newPosAnsC);
	}
	
	@Test
	public void testIsValidSuccess() {
		assertTrue(exampleQuestion.isValid());
	}
	
	@Test
	public void testIsValidTextFail() {
		exampleQuestion.setQuestionText(null);
		assertFalse(exampleQuestion.isValid());
		exampleQuestion.setQuestionText("");
		assertFalse(exampleQuestion.isValid());
	}
	
	@Test
	public void testIsValidAnswersFail() {
		exampleQuestion.setPossibleAnswers(null);
		assertFalse(exampleQuestion.isValid());
		exampleQuestion.setPossibleAnswers(Collections.<Character, PossibleAnswer>emptyMap());
		assertFalse(exampleQuestion.isValid());
	}
	
	@Test
	public void testIsValidCorrectAnswersFail() {
		Map<Character, PossibleAnswer> posAnsMap = new HashMap<Character, PossibleAnswer>();
		posAnsMap.put('a', Fixtures.getNewPossibleAnswerImpl("Yes", 'a', false, 1, 100l));
		exampleQuestion.setPossibleAnswers(posAnsMap);
		assertFalse(exampleQuestion.isValid());
	}
}


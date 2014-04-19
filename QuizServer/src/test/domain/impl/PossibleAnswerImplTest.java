package domain.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Fixtures.Fixtures;
import domain.PossibleAnswer;

public class PossibleAnswerImplTest {

	PossibleAnswer examplePosAns;
	
	@Before
	public void setUp() {
		examplePosAns = Fixtures.getNewPossibleAnswerImpl("A", 'a', true, 1, 100l);
	}
	
	@Test
	public void testIsValidSuccess() {
		assertTrue(examplePosAns.isValid());
	}
	
	@Test
	public void testIsValidTextFail() {
		examplePosAns.setAnswerText(null);
		assertFalse(examplePosAns.isValid());
		examplePosAns.setAnswerText("");
		assertFalse(examplePosAns.isValid());
	}
	
	@Test
	public void testIsValidCharacterFail() {
		examplePosAns.setAnswerCharacter(null);
		assertFalse(examplePosAns.isValid());
	}
}

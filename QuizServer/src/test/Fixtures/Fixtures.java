package Fixtures;
import java.util.HashMap;
import java.util.Map;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import domain.Score;
import domain.impl.PossibleAnswerImpl;
import domain.impl.QuestionImpl;
import domain.impl.QuizImpl;
import domain.impl.ScoreImpl;


public class Fixtures {

	static public PossibleAnswer getNewPossibleAnswerImpl(String text, Character character, boolean correct, Integer questionNumber, Long quizId) {
		PossibleAnswer posAns = new PossibleAnswerImpl();
		posAns.setAnswerText(text);
		posAns.setAnswerCharacter(character);
		posAns.setCorrect(correct);
		posAns.setQuestionNumber(questionNumber);
		posAns.setQuizId(quizId);
		return posAns;
	}
	
	static public Question getNewQuestionImpl(String text, Integer number, Long quizId) {
		Question q = new QuestionImpl();
		q.cascadeSetQuestionNumber(number);
		q.setQuestionText(text);
		q.setQuizId(quizId);
		return q;
	}
	
	static public Score getNewScoreImpl(int amount, String name, Long quizId) {
		Score score = new ScoreImpl();
		score.setAmount(amount);
		score.setName(name);
		return score;
	}
	
	static public Quiz getNewQuizImpl(String title, int maxScore, Long id) {
		Quiz quiz = new QuizImpl();
		quiz.setId(id);
		quiz.setMaxScore(maxScore);
		quiz.setTitle(title);
		return quiz;
	}
	
	static public Quiz getExampleQuizImpl() {
		Quiz quiz = getNewQuizImpl("Letter Quiz", 3, 100l);
		Question q1 = getNewQuestionImpl("A or B?", 1, 100l);
		Map<Character, PossibleAnswer> ansMap1 = new HashMap<Character, PossibleAnswer>();
		ansMap1.put('a', getNewPossibleAnswerImpl("A", 'a', true, 1, 100l));
		ansMap1.put('b', getNewPossibleAnswerImpl("B", 'b', false, 1, 100l));
		q1.setPossibleAnswers(ansMap1);
		Question q2 = getNewQuestionImpl("C or D?", 2, 100l);
		Map<Character, PossibleAnswer> ansMap2 = new HashMap<Character, PossibleAnswer>();
		ansMap2.put('a', getNewPossibleAnswerImpl("C", 'a', true, 2, 100l));
		ansMap2.put('b', getNewPossibleAnswerImpl("D", 'b', false, 2, 100l));
		q2.setPossibleAnswers(ansMap2);
		Question q3 = getNewQuestionImpl("E or F?", 3, 100l);
		Map<Character, PossibleAnswer> ansMap3 = new HashMap<Character, PossibleAnswer>();
		ansMap3.put('a', getNewPossibleAnswerImpl("E", 'a', true, 3, 100l));
		ansMap3.put('b', getNewPossibleAnswerImpl("F", 'b', false, 3, 100l));
		q3.setPossibleAnswers(ansMap3);
		Map<Integer, Question> qMap = new HashMap<Integer, Question>();
		qMap.put(1, q1);
		qMap.put(2, q2);
		qMap.put(3, q3);
		quiz.setQuestions(qMap);
		Score score = getNewScoreImpl(2, "Dave", 100l);
		quiz.setTopScore(score);
		return quiz;
	}
}

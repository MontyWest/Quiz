package runner;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import domain.Score;

public class QuizPlayRunnerTest {
	
	Quiz testQuiz;
	
	@Before
	public void setUp() {
		PossibleAnswerInnerImpl posAns1 = new PossibleAnswerInnerImpl();
		posAns1.setAnswerCharacter('a');
		posAns1.setAnswerText("The letter A");
		posAns1.setCorrect(true);
		posAns1.setQuestionNumber(1);
		posAns1.setQuizId(100l);
		posAns1.setValid(true);
		PossibleAnswerInnerImpl posAns2 = new PossibleAnswerInnerImpl();
		posAns2.setAnswerCharacter('b');
		posAns2.setAnswerText("The letter B");
		posAns2.setCorrect(false);
		posAns2.setQuestionNumber(1);
		posAns2.setQuizId(100l);
		posAns2.setValid(true);
		Map<Character, PossibleAnswer> ansMap = new HashMap<Character, PossibleAnswer>();
		ansMap.put('a', posAns1);
		ansMap.put('b', posAns2);
		QuestionInnerImpl question = new QuestionInnerImpl();
		question.setPossibleAnswers(ansMap);
		question.setQuestionNumber(1);
		question.setQuestionText("Pick a letter!");
		question.setQuizId(100l);
		question.setValid(true);
		Map<Integer, Question> questionMap = new HashMap<Integer, Question>();
		questionMap.put(1, question);
		questionMap.put(2, question);
		QuizInnerImpl quiz = new QuizInnerImpl();
		quiz.setId(100l);
		quiz.setMaxScore(1);
		quiz.setQuestions(questionMap);
		quiz.setTitle("Letter Quiz");
		quiz.setTopScore(null);
		quiz.setValid(true);
		this.testQuiz = quiz;
	}

	@Test
	public void testsStart() {
		//TODO: Override System.in so input can be specified and multiple tests can be run
		QuizPlayRunner qpr = new QuizPlayRunner(testQuiz);
		qpr.start();
		int score = qpr.getScoreAmount();
		assertTrue(score >= 0 && score <= 2);
	}
	
	@Test
	public void testsStartWeirdMapKeys() {
		PossibleAnswerInnerImpl posAns3 = new PossibleAnswerInnerImpl();
		posAns3.setAnswerCharacter('?');
		posAns3.setAnswerText("The letter ?");
		posAns3.setCorrect(false);
		posAns3.setQuestionNumber(1);
		posAns3.setQuizId(100l);
		posAns3.setValid(true);
		Map<Character, PossibleAnswer> ansMap = new HashMap<Character, PossibleAnswer>();
		ansMap.put('?', posAns3);
		QuestionInnerImpl question = new QuestionInnerImpl();
		question.setPossibleAnswers(ansMap);
		question.setQuestionNumber(6);
		question.setQuestionText("Pick a letter!");
		question.setQuizId(100l);
		question.setValid(true);
		testQuiz.getQuestions().put(6, question);

		QuizPlayRunner qpr = new QuizPlayRunner(testQuiz);
		qpr.start();
		int score = qpr.getScoreAmount();
		assertTrue(score >= 0 && score <= 3);
	}
	
	class QuizInnerImpl implements Quiz {
		private Long id;
		private String title;
		private Map<Integer, Question> questions;
		private int maxScore;
		private Score topScore;
		private boolean valid;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Map<Integer, Question> getQuestions() {
			return questions;
		}
		public void setQuestions(Map<Integer, Question> questions) {
			this.questions = questions;
		}
		public Question getQuestion(Integer questionNumber) {
			return this.questions.get(questionNumber);
		}
		public int getMaxScore() {
			return maxScore;
		}
		public void setMaxScore(int maxScore) {
			this.maxScore = maxScore;
		}
		public Score getTopScore() {
			return topScore;
		}
		public void setTopScore(Score topScore) {
			this.topScore = topScore;
		}
		public boolean isValid() {
			return valid;
		}
		public void setValid(boolean valid) {
			this.valid = valid;
		}
		public String toString() {
			return this.id + ": " + this.title;
		}
	}
	
	class QuestionInnerImpl implements Question {
		private String questionText;
		private Map<Character, PossibleAnswer> possibleAnswers;
		private Long quizId;
		private Integer questionNumber;
		private boolean valid;
		public String getQuestionText() {
			return questionText;
		}
		public void setQuestionText(String questionText) {
			this.questionText = questionText;
		}
		public Map<Character, PossibleAnswer> getPossibleAnswers() {
			return possibleAnswers;
		}
		public PossibleAnswer getPossibleAnswer(Character answerCharacter) {
			return this.possibleAnswers.get(answerCharacter);
		}
		public void setPossibleAnswers(Map<Character, PossibleAnswer> possibleAnswers) {
			this.possibleAnswers = possibleAnswers;
		}
		public Long getQuizId() {
			return quizId;
		}
		public void setQuizId(Long quizId) {
			this.quizId = quizId;
		}
		public Integer getQuestionNumber() {
			return questionNumber;
		}
		public void setQuestionNumber(Integer questionNumber) {
			this.questionNumber = questionNumber;
		}
		public boolean isValid() {
			return valid;
		}
		public void setValid(boolean valid) {
			this.valid = valid;
		}
		public String toString() {
			return this.questionNumber + ": " + this.questionText;
		}
	}
	
	class PossibleAnswerInnerImpl implements PossibleAnswer {
		private String answerText;
		private boolean correct;
		private Integer questionNumber;
		private Long quizId;
		private Character answerCharacter;
		private boolean valid;
		public String getAnswerText() {
			return answerText;
		}
		public void setAnswerText(String answerText) {
			this.answerText = answerText;
		}
		public boolean isCorrect() {
			return correct;
		}
		public void setCorrect(boolean correct) {
			this.correct = correct;
		}
		public Integer getQuestionNumber() {
			return questionNumber;
		}
		public void setQuestionNumber(Integer questionNumber) {
			this.questionNumber = questionNumber;
		}
		public Long getQuizId() {
			return quizId;
		}
		public void setQuizId(Long quizId) {
			this.quizId = quizId;
		}
		public Character getAnswerCharacter() {
			return answerCharacter;
		}
		public void setAnswerCharacter(Character answerCharacter) {
			this.answerCharacter = answerCharacter;
		}
		public boolean isValid() {
			return valid;
		}
		public void setValid(boolean valid) {
			this.valid = valid;
		}
		public String toString() {
			return this.answerCharacter + ") " + this.answerText;
		}
	}
}

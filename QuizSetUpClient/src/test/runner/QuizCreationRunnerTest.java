package runner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import domain.Score;
import factory.QuizFactory;

public class QuizCreationRunnerTest {

	private QuizCreationRunner qcr;
	
	@Before
	public void setUp() {
		qcr = new QuizCreationRunner(new QuizFactoryInnerImpl());
	}
	
	@Test
	public void testsLaunch() {
		//TODO override System.in to allow tests on specific input.
		qcr.launch();
	}
	
	class QuizFactoryInnerImpl implements QuizFactory {
		public Quiz getEmptyQuiz() {
			return new QuizInnerImpl();
		}
		public Question getEmptyQuestion() {
			return new QuestionInnerImpl();
		}
		public PossibleAnswer getEmptyPossibleAnswer() {
			return new PossibleAnswerInnerImpl();
		}
	}
	
	class QuizInnerImpl implements Quiz {
		private Long id;
		private String title;
		private Map<Integer, Question> questions = new HashMap<Integer, Question>();
		private int maxScore;
		private Score topScore;
		private boolean valid = true;
		private AtomicInteger lastQuestionNumber = new AtomicInteger();

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
		public Integer addQuestion(Question question) {
			Integer questionNumber = this.lastQuestionNumber.incrementAndGet();
			((QuestionInnerImpl)question).setQuestionNumber(questionNumber);
			this.questions.put(questionNumber, question);
			setMaxScore(this.questions.size());
			return questionNumber;
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
		private Map<Character, PossibleAnswer> possibleAnswers = new HashMap<Character, PossibleAnswer>();
		private Long quizId;
		private Integer questionNumber;
		private boolean valid = true;
		private AtomicInteger lastAnswerCharInt = new AtomicInteger();
		public QuestionInnerImpl(){
			this.lastAnswerCharInt.set(((int)'a')-1);
		}
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
		public Character addPossibleAnswer(PossibleAnswer possibleAnswer) {
			Character answerCharacter = (char)(this.lastAnswerCharInt.incrementAndGet());
			((PossibleAnswerInnerImpl)possibleAnswer).setAnswerCharacter(answerCharacter);
			this.possibleAnswers.put(answerCharacter, possibleAnswer);
			return answerCharacter;
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
		public void cascadeSetQuestionNumber(Integer questionNumber) {
			this.questionNumber = questionNumber;
			for (PossibleAnswer possibleAnswer : possibleAnswers.values()) {
				possibleAnswer.setQuestionNumber(questionNumber);
			}
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
		private boolean valid = true;
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

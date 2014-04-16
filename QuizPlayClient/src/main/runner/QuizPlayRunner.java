package runner;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeSet;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;

public class QuizPlayRunner {

	private Quiz quiz;
	private int scoreAmount;
	PrintStream o = System.out;
	Scanner i = new Scanner(System.in);
	
	public QuizPlayRunner(Quiz quiz) {
		this.quiz = quiz;
		this.scoreAmount = 0;
	}
	
	public void start() {
		o.println("\nQuiz " + quiz);
		TreeSet<Integer> questionNumbers = new TreeSet<Integer>(quiz.getQuestions().keySet());
		for (Integer i : questionNumbers) {
			Question question = quiz.getQuestion(i);
			answerQuestion(question);
		}
		o.println("\nPress enter to finish quiz.");
		i.nextLine();
	}
	
	private void answerQuestion(Question question) {
		o.println("  Question " + question);
		TreeSet<Character> answerChars = new TreeSet<Character>(question.getPossibleAnswers().keySet());
		for (Character c : answerChars) {
			PossibleAnswer possibleAnswer = question.getPossibleAnswer(c);
			o.println("    " + possibleAnswer);
		}
		PossibleAnswer possibleAnswer = null;
		while (possibleAnswer == null) {
			o.print("  Answer (enter character): ");
			char choice = i.nextLine().charAt(0);
			possibleAnswer = question.getPossibleAnswer(choice);
			if (possibleAnswer == null) {
				o.println("    Answer doesn't exit, try again.");
			}
		}
		if (possibleAnswer.isCorrect()) {
			this.scoreAmount++;
		}
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	public int getScoreAmount() {
		return scoreAmount;
	}
	
	
}

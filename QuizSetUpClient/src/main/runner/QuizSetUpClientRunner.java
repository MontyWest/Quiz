package runner;

import remote.QuizServerController;
import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import factory.QuizFactory;

public class QuizSetUpClientRunner {

	QuizServerController server;
	QuizFactory quizFactory;
	
	public QuizSetUpClientRunner(QuizServerController server, QuizFactory quizFactory) {
		this.server = server;
		this.quizFactory = quizFactory;
	}
	
	public void start() {
		System.out.println("start called.");
		this.menu();
		
		Quiz quiz = quizFactory.getEmptyQuiz();
		quiz.setTitle("Test quiz");
		Question question1 = quizFactory.getEmptyQuestion();
		question1.setQuestionText("A or B?");
		PossibleAnswer possibleAnswer1a = quizFactory.getEmptyPossibleAnswer();
		possibleAnswer1a.setAnswerText("A");
		possibleAnswer1a.setCorrect(true);
		question1.addPossibleAnswer(possibleAnswer1a);
		PossibleAnswer possibleAnswer1b = quizFactory.getEmptyPossibleAnswer();
		possibleAnswer1a.setAnswerText("B");
		question1.addPossibleAnswer(possibleAnswer1b);
		quiz.addQuestion(question1);
		System.out.print(quiz);
		try {
			Long id = server.recieveNewQuiz(quiz);
			System.out.println("Sucessful add: " + id);
			System.out.print(server.getQuizListDisplay());
			Quiz getQuiz = server.getQuiz(id);
			if(getQuiz == quiz) {System.out.println("Same");}
			else {System.out.println("Not Same");
			}
			System.out.println(quiz);
			System.out.println(server.recieveScoreForQuiz(id, 1, "ME"));
			System.out.println(server.closeQuiz(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void menu() {
		
	}
	
}

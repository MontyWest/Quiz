package runner;

import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Set;

import remote.QuizServerController;
import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import exception.QuizInvalidException;
import exception.QuizNotFoundException;
import factory.QuizFactory;

public class QuizSetUpClientRunner {

	QuizServerController server;
	QuizFactory quizFactory;
	PrintStream o = System.out;
	Scanner i = new Scanner(System.in);
	
	public QuizSetUpClientRunner(QuizServerController server, QuizFactory quizFactory) {
		this.server = server;
		this.quizFactory = quizFactory;
	}
	
	public void start() {
		System.out.println("Add dummy quiz? (y/n)");
		if (i.nextLine().equals("y")) {
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
		} else {
			this.menu();
		}
		o.println("Quiz Set Up shutting down");
	}
	
	private void menu() {
		String cont;
		do {
			o.println("\n### Menu ###\n");
			o.println("1. View Quiz List");
			o.println("2. Create Quiz");
			o.println("3. Close Quiz");
			o.println("4. Quit");
			int choice = 0;
			while (choice < 1 || choice > 4) {
				o.print("Please enter an option: ");
				try {
					choice = Integer.parseInt(i.nextLine());
				} catch (NumberFormatException e) {
					o.println("You must enter a number.");
				}
			}
			switch (choice) {
			case 1:
				printQuizList();
				break;
			case 2:
				createQuiz();
				break;
			case 3:
				closeQuiz();
				break;
			}
			o.println("Do you wish to quit? (y/n)");
			cont =  i.nextLine();
		} while (!cont.equals("y"));
	}
	
	private void printQuizList() {
		o.println("\n# Quiz List #\n");
		try {
			o.print(server.getQuizListDisplay());
		} catch (RemoteException e) {
			o.println("Server error" + e.getMessage());
		}
	}
	
	private void createQuiz() {
		QuizCreationRunner quizCreationRunner = new QuizCreationRunner(quizFactory);
		quizCreationRunner.launch();
		Quiz quiz = quizCreationRunner.getQuiz();
		if (quiz.isValid()) {
			o.println("Would you like to upload your quiz? (y/n)");
			String send = i.nextLine();
			while (send.equals("y")) {
				send = "n";
				try {
					Long id = server.recieveNewQuiz(quiz);
					o.println("Congratulations your quiz has bee added, id:" + id);
				} catch (RemoteException e) {
					o.println("Server error, please try again. " + e.getMessage());
					o.println("Would you like to try to send again? (y/n)");
					send = i.nextLine();
				} catch (QuizInvalidException e) {
					o.println("Quiz Invalid, sending failed.");
				} catch (IllegalArgumentException e) {
					o.println("Empty quiz sent, not added.");
				}
			}
		}
	}
	
	private void closeQuiz() {
		printQuizList();
		try {
			Long choice = 0l;
			Set<Long> quizIds = server.getQuizIds();
			while (!quizIds.contains(choice)) {
				o.print("Which quiz would you like to close? [WARNING: When closed no one will be able to play this quiz.");
				try {
					choice = Long.parseLong(i.nextLine());
				} catch (NumberFormatException e) {
					o.println("You must enter an id.");
				}
				try {
					Quiz closedQuiz = server.closeQuiz(choice);
					o.println("Top Score for Quiz: " + closedQuiz.getTitle() + " was");
					o.println(closedQuiz.getTopScore());
					o.println("Out of " + closedQuiz.getMaxScore());
				} catch (QuizNotFoundException e) {
					o.println(e.getMessage());
				}
			}
		} catch (RemoteException e) {
			o.println("Server error" + e.getMessage());
		}
	}
}

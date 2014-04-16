package runner;

import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Set;

import domain.Quiz;
import exception.QuizInvalidException;
import exception.QuizNotFoundException;
import remote.QuizServerController;

public class QuizPlayClientRunner {

	QuizServerController server;
	PrintStream o = System.out;
	Scanner i = new Scanner(System.in);

	public QuizPlayClientRunner(QuizServerController server) {
		this.server = server;
	}

	public void start() {
		// TODO
		this.menu();
		o.println("Quiz Player shutting down.");
	}

	private void menu() {
		String cont;
		do {
			o.println("\n### Menu ###\n");
			o.println("1. View Quiz List");
			o.println("2. Play Quiz");
			o.println("3. Quit");
			int choice = 0;
			while (choice < 1 || choice > 3) {
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
				playQuiz();
				break;
			}
			o.println("Do you wish to quit? (y/n)");
			cont = i.nextLine();
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

	private void playQuiz() {
		Quiz quiz;
		try {
			Set<Long> quizIds = server.getQuizIds();
			if (quizIds == null || quizIds.isEmpty()) {
				o.println("There are no quizzes to play.");
				return;
			}
			printQuizList();
			Long choice = 0l;
			while (!quizIds.contains(choice)) {
				o.print("Which quiz would you like to play? (Enter id): ");
				try {
					choice = Long.parseLong(i.nextLine());
				} catch (NumberFormatException e) {
					o.println("You must enter an id.");
				}
			}
			try {
				quiz = server.getQuiz(choice);
			} catch (RemoteException e) {
				o.println("Server error getting quiz" + e.getMessage());
				return;
			}

			o.println("Quiz: " + quiz.getTitle());
			o.println("Top Score: " + quiz.getTopScore());
			o.println("Out of " + quiz.getMaxScore());
			QuizPlayRunner quizPlayRunner = new QuizPlayRunner(quiz);
			quizPlayRunner.start();
			int scoreAmount = quizPlayRunner.getScoreAmount();
			o.println("You scored: " + scoreAmount);
			o.print("Please enter you name: ");
			String scoreName = i.nextLine();
			
			try {
				boolean topScore = server.recieveScoreForQuiz(quiz.getId(), scoreAmount, scoreName);
				if (topScore) {
					o.println("You got the top score, congratulations!");
				} else {
					o.println("You didn't get the top score.");
				}
			} catch (RemoteException e) {
				o.println("Server error sending score" + e.getMessage());
			}
		} catch (QuizInvalidException e) {
			o.println(e.getMessage());
		} catch (QuizNotFoundException e) {
			o.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			o.println(e.getMessage());
		} catch (RemoteException e) {
			o.println("Server error" + e.getMessage());
		}
	}
}

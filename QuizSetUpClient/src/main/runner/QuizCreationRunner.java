package runner;

import java.io.PrintStream;
import java.util.Scanner;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import factory.QuizFactory;

public class QuizCreationRunner {

	private QuizFactory quizFactory;
	private Quiz quiz;
	private PrintStream o = System.out;
	private Scanner i = new Scanner(System.in);
	
	public QuizCreationRunner(QuizFactory quizFactory) {
		this.quizFactory = quizFactory;
		this.quiz = this.quizFactory.getEmptyQuiz();
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public void launch() {
		setTitleForQuiz();
		quizMenu();
	}
	
	private void quizMenu() {
		boolean cont = true;
		do {
			o.println("\n# Quiz Menu #\n");
			o.println("1. View Quiz");
			o.println("2. Edit Quiz Title");
			o.println("3. Add new Question");
			o.println("4. Edit Question");
			o.println("5. Exit Quiz creation");
			int choice = 0;
			while (choice < 1 || choice > 5) {
				o.print("Please enter an option: ");
				try {
					choice = Integer.parseInt(i.nextLine());
				} catch (NumberFormatException e) {
					o.println("You must enter a number.");
				}
			}
			switch (choice) {
			case 1:
				printQuiz();
				break;
			case 2:
				setTitleForQuiz();
				break;
			case 3:
				createQuestion();
				break;
			case 4:
				editQuestion();
				break;
			case 5:
				o.println("You won't be able to edit your quiz again, are you sure? (y/n)");
				if (i.nextLine().equals("y")) {
					if (quiz.isValid()) {
						cont = false;
					} else {
						o.println("Your quiz is invalid, it will not be uploaded, you may still edit it, do you still wish to exit? (y/n)");
						cont = !(i.nextLine().equals("y"));
					}
				}
				break;
			}
		} while (cont);
	}
	
	private void printQuiz() {
		o.println("\nQuiz: " + quiz.getTitle());
		for (int i = 1; i <= quiz.getQuestions().size(); i++) {
			Question question = quiz.getQuestion(i);
			printQuestion(question);
		}
		o.println("\nPress enter when done");
		i.nextLine();
	}
	
	private void setTitleForQuiz() {
		if (!(quiz.getTitle() == null)) {
			o.println("Current title: " + quiz.getTitle());
		}
		o.println("Enter a title for your quiz: ");
		String title = i.nextLine();
		quiz.setTitle(title);
	}
	
	private void createQuestion() {
		Question question = quizFactory.getEmptyQuestion();
		setTextForQuestion(question);
		questionMenu(question);
	}
	
	private void editQuestion() {
		int choice = 0;
		o.print("Enter a quesiton number to edit: ");
		try {
			choice = Integer.parseInt(i.nextLine());
		} catch (NumberFormatException e) {
			o.println("You must enter a number.");
			return;
		}
		Question question = quiz.getQuestion(choice);
		if (question == null) {
			o.println("Question doesn't exist");
		} else {
			questionMenu(question);
		}
	}
	
	private void questionMenu(Question question) {
		boolean cont = true;
		do {
			o.println("\n# Question Menu #\n");
			o.println("1. View Question");
			o.println("2. Edit Question Text");
			o.println("3. Add new Possible Answer");
			o.println("4. Edit Possible Answe");
			o.println("5. Exit Question menu and add Question to Quiz");
			int choice = 0;
			while (choice < 1 || choice > 5) {
				o.print("Please enter an option: ");
				try {
					choice = Integer.parseInt(i.nextLine());
				} catch (NumberFormatException e) {
					o.println("You must enter a number.");
				}
			}
			switch (choice) {
			case 1:
				printQuestion(question);
				break;
			case 2:
				setTextForQuestion(question);
				break;
			case 3:
				addPossibleAnswer(question);
				break;
			case 4:
				editPossibleAnswer(question);
				break;
			case 5:
				addQuestionToQuiz(question);
				cont = false;
				break;
			}
		} while (cont);
	}
	
	private void printQuestion(Question question) {
		if (question == null) {
			o.println("Null Question");
			return;
		}
		o.println("  Question " + question.getQuestionNumber() + ": " + question.getQuestionText());
		for (int i = 0; i < question.getPossibleAnswers().size(); i++) {
			Character c = (char)('a' + i);
			PossibleAnswer possibleAnswer = question.getPossibleAnswer(c);
			o.print("    Answer " + possibleAnswer.getAnswerCharacter() + ": " + possibleAnswer.getAnswerText());
			String correct = " ";
			if(possibleAnswer.isCorrect()) {
				correct = " [T]";
			}
			o.println(correct);
		}
	}
	
	private void setTextForQuestion(Question question) {
		if (!(question.getQuestionText() == null)) {
			o.println("Current text: " + question.getQuestionText());
		}
		o.println("Enter the text for your question: ");
		String questionText = i.nextLine();
		question.setQuestionText(questionText);
	}

	private void addQuestionToQuiz(Question question) {
		int questionNumber = quiz.addQuestion(question);
		o.println("Question added, number: " + questionNumber);
	}
	
	private void addPossibleAnswer(Question question) {
		PossibleAnswer possibleAnswer = quizFactory.getEmptyPossibleAnswer();
		setTextForPossibleAnswer(possibleAnswer);
		setCorrectForPossibleAnswer(possibleAnswer);
		Character answerChar = question.addPossibleAnswer(possibleAnswer);
		o.println("Possible Answer added to question, character: " + answerChar);
	}
	
	private void editPossibleAnswer(Question question) {
		char choice = '.';
		o.print("Enter a quesiton number to edit: ");
		choice = i.nextLine().charAt(0);
		PossibleAnswer possibleAnswer = question.getPossibleAnswer(choice);
		if (possibleAnswer == null) {
			o.println("Answer doesn't exist");
		} else {
			o.println("Edit Answer text? (y/n)");
			if (i.nextLine().equals("y")) {
				setTextForPossibleAnswer(possibleAnswer);
			}
			o.println("Edit whether answer is correct? (y/n)");
			if (i.nextLine().equals("y")) {
				setCorrectForPossibleAnswer(possibleAnswer);
			}
		}
	}
	
	private void setTextForPossibleAnswer(PossibleAnswer possibleAnswer) {
		if (!(possibleAnswer.getAnswerText() == null)) {
			o.println("Current text: " + possibleAnswer.getAnswerText());
		}
		o.println("Enter the text for the answer: ");
		String answerText = i.nextLine();
		possibleAnswer.setAnswerText(answerText);
	}
	
	private void setCorrectForPossibleAnswer(PossibleAnswer possibleAnswer) {
		o.println("Currently " + possibleAnswer.isCorrect());
		o.println("Is this answer correct? (y/n)");
		boolean answerCorrect = i.nextLine().equals("y");
		possibleAnswer.setCorrect(answerCorrect);
	}
	
}
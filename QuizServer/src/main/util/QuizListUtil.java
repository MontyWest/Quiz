package util;

import domain.PossibleAnswer;
import domain.Question;
import domain.Quiz;
import domain.QuizList;
import domain.impl.QuizListImpl;
import factory.QuizFactory;
import factory.impl.QuizFactoryImpl;

public class QuizListUtil {

	public static void loadExampleQuizIfEmpty() {
		QuizList quizList = QuizListImpl.getInstance();
		if (quizList.getQuizzes().isEmpty()) {
			QuizFactory quizFactory = new QuizFactoryImpl();
			PossibleAnswer posAnsA = quizFactory.getEmptyPossibleAnswer();
			posAnsA.setAnswerText("A");
			posAnsA.setCorrect(true);
			PossibleAnswer posAnsB = quizFactory.getEmptyPossibleAnswer();
			posAnsA.setAnswerText("B");
			posAnsA.setCorrect(false);
			Question question = quizFactory.getEmptyQuestion();
			question.setQuestionText("A or B?");
			question.addPossibleAnswer(posAnsA);
			question.addPossibleAnswer(posAnsB);
			Quiz quiz = quizFactory.getEmptyQuiz();
			quiz.setTitle("Letter Quiz!");
			quiz.addQuestion(question);
			quizList.addQuiz(quiz);
			System.out.println("Example quiz loaded");
		}
	}
}

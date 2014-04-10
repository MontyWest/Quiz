package remote.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import remote.QuizServerController;
import service.QuizPlayService;
import service.QuizSetUpService;
import service.impl.QuizPlayServiceImpl;
import service.impl.QuizSetUpServiceImpl;
import domain.Quiz;
import domain.Score;
import exception.QuizInvalidException;
import exception.QuizNotFoundException;
import factory.QuizFactory;

public class QuizServerControllerImpl extends UnicastRemoteObject implements QuizServerController {


	private static final long serialVersionUID = 30L;
	
	private QuizPlayService playService = new QuizPlayServiceImpl();
	private QuizSetUpService setUpService = new QuizSetUpServiceImpl();
	
	
	public QuizServerControllerImpl() throws RemoteException {}
	
	@Override
	public String getQuizListDisplay() throws RemoteException {
		log("Quiz List Display requested.");
		return playService.getQuizListDisplay();
	}
	
	@Override
	public Set<Long> getQuizIds() throws RemoteException {
		log("Quiz Ids requested.");
		return playService.getQuizIdSet();
	}

	@Override
	public Quiz getQuiz(Long quizId) throws RemoteException,
			QuizInvalidException {
		log("Quiz " + quizId + " requested.");
		return playService.getQuiz(quizId);
	}

	@Override
	public boolean recieveScoreForQuiz(Long quizId, int scoreAmount, String scoreName)
			throws RemoteException, QuizInvalidException, QuizNotFoundException, IllegalArgumentException {
		log("Score recieved for quiz " + quizId);
		if (scoreAmount < 0 || scoreName == null) {
			throw new IllegalArgumentException();
		}
		log("Score " + scoreAmount + " sent by " + scoreName + " for quiz " + quizId);
		return playService.updateQuizWithScore(quizId, scoreAmount, scoreName);
	}

	@Override
	public QuizFactory getQuizFactory() throws RemoteException {
		log("Get Quiz Factory requested.");
		return setUpService.getQuizFactory();
	}

	@Override
	public Long recieveNewQuiz(Quiz quiz) throws RemoteException,
			QuizInvalidException, IllegalArgumentException {
		log("Quiz recieved.");
		if (quiz == null) {
			throw new IllegalArgumentException();
		}
		if (quiz.isValid()) {
			Long id = setUpService.addQuiz(quiz);
			log("Quiz added, id: " + id);
			return id;
		} else {
			log("Quiz invalid.");
			throw new QuizInvalidException();
		}
	}

	@Override
	public Score closeQuiz(Long quizId) throws RemoteException,
			QuizNotFoundException {
		log("Close quiz " + quizId + " requested.");
		Quiz quiz = setUpService.removeQuiz(quizId);
		if (quiz == null) {
			throw new QuizNotFoundException("ID: " + quizId);
		} else {
			Score topScore = quiz.getTopScore();
			log("Quiz " + quizId + " closed, top score: " + topScore.getAmount() + " by " + topScore.getName());
			return topScore;
		}
	}
	
	private void log(String message) {
		System.out.println(message);
	}

}

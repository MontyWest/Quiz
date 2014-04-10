package launcher;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;

import remote.QuizServerController;
import runner.QuizSetUpClientRunner;
import factory.QuizFactory;

public class QuizSetUpClientLauncher {
	
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			}
		try {
			Remote service = Naming.lookup("//127.0.0.1:1099/quiz");
			QuizServerController server = (QuizServerController) service;
			QuizFactory quizFactory = server.getQuizFactory();
			QuizSetUpClientRunner runner = new QuizSetUpClientRunner(server, quizFactory);
			runner.start();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}

package launcher;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import persistence.QuizListIO;
import remote.QuizServerController;
import remote.impl.QuizServerControllerImpl;
import util.QuizListUtil;

public class QuizServerLauncher {

	public static void main(String[] args) {
		QuizListIO.loadQuizList();
		QuizListUtil.loadExampleQuizIfEmpty();
		
		launch();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            QuizListIO.saveQuizList();
	        }
	    }, "Shutdown-thread"));
	}

	private static void launch() {

		try {
			// Create the registry
			LocateRegistry.createRegistry(1099);
			// Create the server object
			QuizServerController server = new QuizServerControllerImpl();
			// Register (bind) the server object on the registry.
			// The registry may be on a different machine
			String registryHost = "//localhost/";
			String serviceName = "quiz";
			Naming.rebind(registryHost + serviceName, server);
			System.out.println("Server up!");
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

}

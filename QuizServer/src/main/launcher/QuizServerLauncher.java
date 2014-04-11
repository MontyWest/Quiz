package launcher;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import persistence.QuizListIO;
import remote.QuizServerController;
import remote.impl.QuizServerControllerImpl;

public class QuizServerLauncher {

	public static void main(String[] args) {
		QuizListIO.loadQuizList();
		
		launch();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            QuizListIO.saveQuizList();
	        }
	    }, "Shutdown-thread"));
	}

	private static void launch() {

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			// 2. Create the registry if there is not one
			LocateRegistry.createRegistry(1099);
			// 3. Create the server object
			QuizServerController server = new QuizServerControllerImpl();
			// 4. Register (bind) the server object on the registry.
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

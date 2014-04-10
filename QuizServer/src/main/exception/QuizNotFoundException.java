package exception;

/***
 * Throw if the quiz requested cannot be found.
 * 
 * @author montywest
 *
 */
public class QuizNotFoundException extends Exception {

	private static final long serialVersionUID = 21L;

	public QuizNotFoundException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}

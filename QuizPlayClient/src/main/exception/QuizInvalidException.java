package exception;

/***
 * Throw this exception if the given quiz is unsuitable
 * or invalid, for example if a question has no correct answer.
 * 
 * @author montywest
 *
 */
public class QuizInvalidException extends Exception {

	private static final long serialVersionUID = 20L;

	public QuizInvalidException() {
		super();
	}
	
	public QuizInvalidException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}

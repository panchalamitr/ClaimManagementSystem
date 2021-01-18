package claim.management.system.crudrestfulwebservice.exception;

public class ValidationFailedException extends RuntimeException {

	private static final long serialVersionUID = 3687139424649633149L;

	public ValidationFailedException(String fieldName, String message) {

		super("Invalid "+ fieldName +", " + message);

	}

}

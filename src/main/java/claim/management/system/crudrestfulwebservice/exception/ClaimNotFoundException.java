package claim.management.system.crudrestfulwebservice.exception;

public class ClaimNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3687139424649633149L;

	public ClaimNotFoundException() {

		super("Requested claim details not found!");

	}

}

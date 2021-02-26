package eu.ensup.partiel_spring.exception;

public class ClassNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ClassNotFoundException() {
		this("Implossible de trouvé cet étudiant");
	}

	public ClassNotFoundException(String message) {
		super(message);
	}
	
	public ClassNotFoundException( String message, Throwable cause ) {
		super( message, cause);
	}
	
}

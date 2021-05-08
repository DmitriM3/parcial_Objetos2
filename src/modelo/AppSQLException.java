package modelo;

public class AppSQLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8185135393197705534L;

	public AppSQLException() {
	}

	public AppSQLException(String message) {
		super(message);
	}

	public AppSQLException(Exception e, String message, String string) {
		super(message);
	}

}
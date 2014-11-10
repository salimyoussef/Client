package exceptions;

public class ConnectionNotOpenedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "The connection was not opened";
	}
}

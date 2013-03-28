package com.hyperdao.exception;

/**
 * @author Tim
 * 
 * Reciply Data level exception
 *
 */
public class HyperDAOException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public HyperDAOException() {
		// TODO Auto-generated constructor stub
	}
	
	public HyperDAOException(Throwable cause) {
		super(cause);
	}
	
	public HyperDAOException(String message) {
		super(message);
	}
	
	public HyperDAOException(String message, Throwable cause) {
		super(message, cause);
	}

}

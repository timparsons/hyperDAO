package com.parsons.dao.exception;

/**
 * @author Tim
 * 
 * Reciply Data level exception
 *
 */
public class DAOException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DAOException() {
		// TODO Auto-generated constructor stub
	}
	
	public DAOException(Throwable cause) {
		super(cause);
	}
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

}

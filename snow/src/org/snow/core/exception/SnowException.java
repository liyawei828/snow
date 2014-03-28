/**
 * 
 */
package org.snow.core.exception;

/**
 * @author lyw
 *
 */
@SuppressWarnings("serial")
public class SnowException extends RuntimeException {
	public SnowException() {}
	
	public SnowException(String message) {
		super(message);
	}
	
	public SnowException(Throwable cause) {
		super(cause);
	}
	
	public SnowException(String message, Throwable cause) {
		super(message, cause);
	}

}

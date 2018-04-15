package exceptions;

/**
 * Exception thrown if you try to build a handler that doesnt exist
 * @author Dave
 *
 */
public class HandlerNonExistent extends Exception 
{
	/**svid*/
	private static final long serialVersionUID = -3094072550354189104L;

	/**
	 * Constructor
	 * @param s the error message
	 */
	public HandlerNonExistent(String s) {
		super(s);
	}
}

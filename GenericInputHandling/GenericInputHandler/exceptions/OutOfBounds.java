package exceptions;

/**
 * Exception thrown if your input violates a boundary rule
 * @author Dave
 *
 */
public class OutOfBounds extends Exception 
{
	/**svid*/
	private static final long serialVersionUID = 3694003347393222254L;

	/**
	 * Constructor
	 * @param s the error message
	 */
	public OutOfBounds(String s) 
	{
		super(s);
	}
}

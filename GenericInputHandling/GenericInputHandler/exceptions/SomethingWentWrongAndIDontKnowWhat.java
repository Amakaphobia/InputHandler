package exceptions;

/**
 * Exception thrown if i dont know whats up
 * @author Dave
 *
 */
public class SomethingWentWrongAndIDontKnowWhat extends Exception 
{
	/**svid*/
	private static final long serialVersionUID = 3399398344188610772L;

	/**
	 * Constructor
	 * @param s the error message
	 */
	public SomethingWentWrongAndIDontKnowWhat(String s)
	{
		super(s);
	}
}

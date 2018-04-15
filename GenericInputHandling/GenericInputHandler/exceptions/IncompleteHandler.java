package exceptions;

/**
 * Exception thrown if you try to build a handler that isnt completed yet
 * @author Dave
 *
 */
public class IncompleteHandler extends Exception 
{
	/**svid*/
	private static final long serialVersionUID = -6281380462346946795L;
	
	/**
	 * Constructor
	 * @param s the error message
	 */
	public IncompleteHandler(String s) 
	{
		super(s);
	}

}

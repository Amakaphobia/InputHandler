package exceptions;

/**
 * Exception thrown if your input violates a rule
 * @author Dave
 *
 */
public class BrokenRule extends Exception
{
	/**svid*/
	private static final long serialVersionUID = 5452398659215252706L;
	/**
	 * Constructor
	 * @param s the error message
	 */
	public BrokenRule(String s) {
		super(s);
	}
}

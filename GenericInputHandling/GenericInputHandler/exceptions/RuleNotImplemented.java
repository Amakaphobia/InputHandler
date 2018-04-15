package exceptions;

/**
 * Exception thhrown if you try to implement a validator that doesnt exist
 * @author Dave
 *
 */
public class RuleNotImplemented extends Exception 
{
	/**svid*/
	private static final long serialVersionUID = 4100357904839039805L;

	/**
	 * Constructor
	 * @param s the error message
	 */
	public RuleNotImplemented(String s) {
		super(s);
	}
}

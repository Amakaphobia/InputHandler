package exceptions;

/**
 * Exception used if you try to build something that doesnt exist
 * @author Dave
 *
 */
public class WrongRuleCode extends Exception 
{
	/**svid*/
	private static final long serialVersionUID = 6874898897488143472L;

	/**
	 * Constructor
	 * @param s the error message
	 */
	public WrongRuleCode(String s) {
		super(s);
	}
}

package exceptions;

/**
 * Exception thrown if you try to use a datatype thats not implemented yet
 * @author Dave
 *
 */
public class DatatypeNotImplemented extends Exception
{
	/**svid*/
	private static final long serialVersionUID = -7411745779311074077L;

	/**
	 * Constructor
	 * @param s the error message
	 */
	public DatatypeNotImplemented(String s) {
		super(s);
	}
}

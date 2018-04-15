package logic;

import java.util.function.Predicate;
import exceptions.WrongRuleCode;

/**
 * Class used to describe boundaries for possible value sets
 * @author Dave
 * @param <T> the input type
 */
public class ValidatorBoundary <T>
{
	/**the predicate used to validate*/
	private Predicate<T> Validator;
	/**true if the validator uses bounds*/
	private boolean hasbounds;
	/**array containing the bounds*/
	private double[] bounds;
	
	/**
	 * Constructor
	 * @param Validator the validator to be used
	 */
	public ValidatorBoundary(Predicate<T> Validator)
	{
		this.Validator = Validator;
	}
	
	/**
	 * Constructor
	 * @param Validator the validator to be used
	 * @param hasbounds if this has bounds its true
	 * @param bounds array containing the bounds
	 * @throws WrongRuleCode if the bounds array has the wrong size
	 */
	public ValidatorBoundary(Predicate<T> Validator, boolean hasbounds, double[]bounds) throws WrongRuleCode
	{
		this.Validator = Validator;
		this.hasbounds = hasbounds;
		if(bounds.length == 2)
			this.bounds    = bounds;
		else
			throw new WrongRuleCode("You specified a number of bounds for a single inputrule thats more than 2");
	}
	
	/**Constructor*/
	public ValidatorBoundary() 
	{
	}

	/**
	 * method used to set the validator
	 * @param validator the validator
	 */
	public void setValidator(Predicate<T> validator) {
		Validator = validator;
	}
	
	/**
	 * Method used to set the hasBounds field
	 * @param hasbounds true if it has bounds 
	 */
	public void setHasbounds(boolean hasbounds) {
		this.hasbounds = hasbounds;
	}
	
	/**
	 * Method used to set the bounds
	 * @param bounds the bounds
	 */
	public void setBounds(double[] bounds) {
		this.bounds = bounds;
	}
	
	/**
	 * method used to get the validator
	 * @return the validator field
	 */
	public Predicate<T> getValidator() {
		return this.Validator;
	}

	/**
	 * Method used to get hasBounds
	 * @return the field has bounds
	 */
	public boolean hasbounds() {
		return this.hasbounds;
	}

	/**
	 * Gets the array counting the Bounds
	 * @return the field bounds
	 */
	public double[] getBounds() {
		return this.bounds;
	}
}

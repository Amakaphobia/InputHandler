package factories;

import java.util.function.Predicate;
import exceptions.RuleNotImplemented;
import exceptions.SomethingWentWrongAndIDontKnowWhat;
import exceptions.WrongRuleCode;
import logic.ValidatorBoundary;

/**
 * Class to fabricate new validators
 * @author Dave
 *
 * @param <T> the input type of the to evaluated item
 */
public class ValidatorFactory <T>
{
	/**identifies no validation*/
	public static final int VALID_NON = -1;
	/**identifies greater than*/
	public static final int VALID_GT = 0;
	/**identifies less than*/
	public static final int VALID_LT = 1;
	/**identifies greater than or equal*/
	public static final int VALID_GE = 2;
	/**identifies lesser than or equal*/
	public static final int VALID_LE = 3;
	/**identifies equals*/
	public static final int VALID_EE = 4;
	/**identifies not equals*/
	public static final int VALID_NE = 5;
	/**identifies in between*/
	public static final int VALID_IN_BETWEEN = 6;
	
	/**
	 *  method used to set the validator per identifier without additional parameters
	 * @param ValidatorNum the Identifier
	 * @return the predicate
	 * @throws Exception if you choose something that doesnt exist
	 */
	public ValidatorBoundary<T> choosePredicate(int ValidatorNum) throws Exception	
	{	 
		double[] d = new double[0];
		
		return this.choosePredicate(ValidatorNum, d);
	}
	
	/**
	 *  method used to set the validator per identifier with one additional parameter
	 * @param ValidatorNum the Identifier
	 * @param singleP the single parameter
	 * @return the predicate
	 * @throws Exception if you choose something that doesnt exist
	 */
	public ValidatorBoundary<T> choosePredicate(int ValidatorNum, 
			   int singleP) throws Exception	
	{	 
		double[] d = {(double)singleP};
		
		return this.choosePredicate(ValidatorNum, d);
	}
	
	/**
	 *  method used to set the validator per identifier with more than one additional parameter
	 * @param ValidatorNum the Identifier
	 * @param ValidatorParams the additional parameters
	 * @return the predicate
	 * @throws Exception if you choose something that doesnt exist
	 */
	public ValidatorBoundary<T> choosePredicate(int ValidatorNum, 
										   double[] ValidatorParams) throws Exception	
	{	
		if(ValidatorNum == ValidatorFactory.VALID_IN_BETWEEN && ValidatorParams.length != 2)
			throw new WrongRuleCode
							("ValidInbetween needs bounds splz rework set bounds method in task or be more careful with unsafe injection");
		if(ValidatorParams.length == 0 && ValidatorNum != ValidatorFactory.VALID_NON)
			throw new WrongRuleCode
							("You dont have all Parameters Specified");
		
		ValidatorBoundary<T> P = innerChoose(ValidatorNum, ValidatorParams);
		
		if(P == null)
			throw new SomethingWentWrongAndIDontKnowWhat
							("I dont know what to tell you. look into debugging this stuff");		
		return P; 
	}
	
	/**
	 * Inner Method used to choose a predicate out of a number of validators
	 * @param ValidatorNum the validator identifier
	 * @param ValidatorParams the additional param to be used
	 * @return the predicate 
	 * @throws WrongRuleCode if the rule code is wrong
	 * @throws RuleNotImplemented if the rule isnt implemented 
	 */
	@SuppressWarnings("unchecked")
	private ValidatorBoundary<T> innerChoose(int ValidatorNum, 
										double[] ValidatorParams) throws WrongRuleCode, RuleNotImplemented
	{
		ValidatorBoundary<T> VB = new ValidatorBoundary<>();
		double j = -1;
		if(ValidatorParams.length != 0)
			j = ValidatorParams[0];
		
		if(ValidatorNum != VALID_IN_BETWEEN)
			VB.setHasbounds(false);
		
		switch(ValidatorNum)
		{	
			case VALID_NON:
				VB.setValidator(e -> true);
				break;
			case VALID_GT:
				VB.setValidator((Predicate<T>) gT(j));
				break;
			case VALID_LT:
				VB.setValidator((Predicate<T>) lT(j));
				break;
			case VALID_GE:
				VB.setValidator((Predicate<T>) gE(j));
				break;
			case VALID_LE:
				VB.setValidator((Predicate<T>) lE(j));
				break;
			case VALID_EE:
				VB.setValidator((Predicate<T>) eE(j));
				break;
			case VALID_NE:
				VB.setValidator((Predicate<T>) nE(j));
				break;
			case VALID_IN_BETWEEN:
				if (ValidatorParams.length != 2) 
					throw new WrongRuleCode
									("You dont have the right Parameters Specified");
				VB.setValidator((Predicate<T>) inBetween(j
					,ValidatorParams[1]
				));
				VB.setHasbounds(true);
				VB.setBounds(ValidatorParams);
				break;	
				
			default:
				throw new RuleNotImplemented
					       		("This Rule isnt defined yet");
		}
		return VB;
	}

	/**
	 * Method used to generate a greater than input predicate
	 * @param i the param to be checked against
	 * @return the predicate
	 */
	private static <C extends Number> Predicate<? extends Number> gT(C i)
	{
		return e -> e.doubleValue() > i.doubleValue();		
	}
	
	/**
	 * Method used to generate a less than input predicate
	 * @param i the param to be checked against
	 * @return the predicate
	 */
	private static <C extends Number> Predicate<? extends Number> lT(C i)
	{
		return e -> e.doubleValue() < i.doubleValue();		
	}
	
	/**
	 * Method used to generate a greater or equal than input predicate
	 * @param i the param to be checked against
	 * @return the predicate
	 */
	private static <C extends Number> Predicate<? extends Number> gE(C i)
	{
		return e -> e.doubleValue() >= i.doubleValue();		
	}
	
	/**
	 * Method used to generate a less or equal than input predicate
	 * @param i the param to be checked against
	 * @return the predicate
	 */
	private static <C extends Number> Predicate<? extends Number> lE(C i)
	{
		return e -> e.doubleValue() <= i.doubleValue();		
	}	
	
	/**
	 * Method used to generate a equal to input predicate
	 * @param i the param to be checked against
	 * @return the predicate
	 */
	private static <C extends Number> Predicate<? extends Number> eE(C i)
	{
		return e -> e.doubleValue() == i.doubleValue();		
	}	

	/**
	 * Method used to generate a not equal to input predicate
	 * @param i the param to be checked against
	 * @return the predicate
	 */
	private static <C extends Number> Predicate<? extends Number> nE(C i)
	{
		return e -> e.doubleValue() != i.doubleValue();		
	}
	
	/**
	 * Method used to generate predicate checking on both sides
	 * @param i1 the lower bound
	 * @param i2 the upper bound
	 * @return the predicate
	 */
	private static <C extends Number> Predicate<? extends Number> inBetween(C i1, C i2)
	{
		return e -> e.doubleValue() >= i1.doubleValue() && e.doubleValue() < i2.doubleValue();
	}
}

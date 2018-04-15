package logic;

import java.io.BufferedReader;
import java.util.function.Function;

import boxes.GenericContainer;

/**
 * Abstract class implemented by all inputhandlers
 * @author Dave
 *
 * @param <T> the expected type of input
 */
public abstract class A_InputHandler<T> implements Cloneable
{
	/**the function used to map from String to the object type*/
	protected Function<T,?> mapper;
	/**the userprompt*/
	protected String Userprompt;
	/**the validator checking the input*/
	protected ValidatorBoundary<T> Validator;
	/**the function used to map from String to the object type*/
	protected Function<String, T> Parser;
	/**target variable for things like file inputs*/
	protected String target;
	/**?*/
	protected short location;
	/**Container holding the input*/
	protected GenericContainer<T> input;
	/**the buffered reader*/
	protected BufferedReader br;
	
	/**
	 * Constructor
	 */
	public A_InputHandler() 
	{
		this.input = new GenericContainer<T>(null);
	}
	
	/**
	 * Method used to get a boundary rule string back if you violated a two sided boundary
	 * @return a error string
	 */
	protected String getBoundaryRule()
	{
		StringBuilder strb = new StringBuilder();
		strb.append("Ough Boundary Violation\n")
		    .append("you need to place youre value inbetween: ")
		    .append(this.Validator.getBounds()[0])
		    .append(" and: ")
		    .append(this.Validator.getBounds()[1])
		    .append(". \nFool");
		return strb.toString();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	/**
	 * Method used to generate the String for error messages
	 * @return String W/ error message
	 */
	public abstract String getUncompleteMessage();
	
	/**
	 * Method used to check if the Handler was correct initialized
	 * @return true if something went wrong
	 */
	public abstract boolean isNotComplete();
	
	/**
	 * method used to handle the input itself
	 */
	public abstract void handleInput();
	
	/**
	 * gets this object back
	 * @return this
	 */
	public A_InputHandler<T> getHandler()
	{ return this; };

	/**
	 * Method used to get correct return value in correct type
	 * @return the value
	 * @param <T> the expected type of value
	 */
	@SuppressWarnings({ "hiding", "unchecked" })
	public <T> T getValue()
	{
		return (T) this.input.getValue();
	}		
	
	/**
	 * this method returns the target string
	 * @return the target
	 */
	public String getTarget()
	{
		return this.target;
	}
	
	/**
	 * gets the validator
	 * @return the predicate
	 */
	public ValidatorBoundary<T> getValidatorBoundary()
	{
		return this.Validator;
	}
	
	/**
	 * MEthod used to access the reader
	 * @return the reader for input
	 */
	public BufferedReader getReader()
	{
		return this.br;
	}
	
	/**
	 * method used to get the parser
	 * @return the parser
	 */
	public Function<?, T> getParser()
	{
		return this.Parser;
	}
	
	/**
	 * Method used to set a target
	 * @param t the target
	 * @return this for chainbuilding
	 */
	public A_InputHandler<?> target(String t)
	{
		this.target = t;
		return this;
	}
	
	/**
	 * method used to set the validator
	 * @param p the predicate
	 * @return this for chainbuilding
	 */
  	public A_InputHandler<T> Validator(ValidatorBoundary<T> p)
	{
		this.Validator = p;
		return this;
	}
  	
  	/**
  	 * Method used to set the input container
  	 * @param i inputcontainer
  	 * @return this for chainbuilding
  	 */
	public A_InputHandler<T> inputContainer(GenericContainer<T> i)
	{
		this.input = i;
		return this;
	}
	
	/**
	 * MEthod used to set the parser
	 * @param f the parser
	 * @return this for chainbuilding
	 */
  	public A_InputHandler<T> Parser(Function<String, T> f)
	{
		this.Parser = f;
		return this;
	}
  	
  	/**
  	 * Method used to set the userprompt
  	 * @param s the question
  	 * @return this for chainbuilding
  	 */
	public A_InputHandler<T> Userprompt(String s)
	{
		this.Userprompt = s;
		return this;
	}
	
	/**
	 * sets the reader
	 * @param br user input
	 * @return this for chainbuilding
	 */
	public A_InputHandler<T> Reader(BufferedReader br)
	{
		this.br = br;
		return this;
	}
	
	/**
	 * gets the userprompt
	 * @return the question
	 */
	public String getUserPrompt()
	{
		return this.Userprompt;
	}

	/**
	 * Method used to give this a number inside of a task
	 * @param inputLocation the location
	 */
	public void setLocation(short inputLocation) {
		this.location = inputLocation;	
	}

	/**
	 * Method used to set a optional mapping function
	 * @param f the mapping function
	 * @return this for chainbuilding
	 */
	public A_InputHandler<T> mapper(Function<T,?> f)
	{
		this.mapper = f;
		return this;
	}
	
	/**
	 * MEthod used to try the input against the validator
	 * @param toTry the input
	 * @return true if it passed the test
	 */
	protected boolean tryInput(T toTry)
	{
		return this.Validator.getValidator().test(toTry);
	}
	
	/**
	 * Used to throw unsupported op exceptions by subclasses
	 */
	protected void unsupportedOp()
	{
		throw new UnsupportedOperationException("this is not allowed");
	}
}

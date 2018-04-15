package factories;

import java.io.BufferedReader;
import java.util.function.Function;
import java.util.function.Predicate;

import boxes.GenericContainer;
import exceptions.HandlerNonExistent;
import exceptions.IncompleteHandler;
import exceptions.SomethingWentWrongAndIDontKnowWhat;
import handlers.ConsoleHandler;
import handlers.FileStreamHandler;
import handlers.ImagesByFolder;
import logic.A_InputHandler;
import logic.ValidatorBoundary;

/**
 * This Factory is used to build the InputHandler itself
 * @author Dave
 * @param <T> the expected input type
 *
 */
public class HandlerFactory<T>
{
	/**ConsoleHandler Identifier*/
	public static final int CONSOLEHANDLER 		= 0;
	/**FILESTREAMHANDLER Identifier*/
	public static final int FILESTREAMHANDLER 	= 1;
	/**IMAGESBYFOLDER Identifier*/
	public static final int IMAGESBYFOLDER		= 2;

	/**The Handler thats being build*/
	private A_InputHandler<T> toModify;
	/**the parserfactory used*/
	private ParserFactory<T> PF;
	/**the validator factory used*/
	private ValidatorFactory<T> VF;
	/**the chosen handler identifier*/
	private int chosenHandler;
	
	
	
	/**
	 * Constructor primes the InputHandler thats to be modified
	 */
	public HandlerFactory() 
	{
		this.PF 		= new ParserFactory<>();
		this.VF			= new ValidatorFactory<>();
	}
	
	/**
	 * Method used to choose the Handler type
	 * @param handlertype the handler identifier
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> chooseHandlerType(int handlertype)
	{
		try
		{
			this.chosenHandler = handlertype;
			switch(chosenHandler)
			{
				case CONSOLEHANDLER:
					this.toModify 	= new ConsoleHandler<>();
					break;
				case FILESTREAMHANDLER:
					this.toModify	= new FileStreamHandler<>();
					break;
				case IMAGESBYFOLDER:
					this.toModify = new ImagesByFolder<>();
					break;
				default:
					throw new HandlerNonExistent
							("Chosen Handler isnt implemented yet");
			}

			return this;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Method used to set a parsing function directly
	 * @param f the parsing function
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> UnsafeSetParser(Function<String, T> f)
	{
		this.toModify.Parser(f);
		return this;
	}
	
	/**
	 * Method used to set a numeric validator directly
	 * @param p the validator to be used of type predicate
	 * @return this for chain building
	 */
	public HandlerFactory<T> UnsafeSetNumValidator(Predicate<T> p)
	{
		this.toModify.Validator(new ValidatorBoundary<T>(p));
		return this;
	}
	
	/**
	 * Method used to set a Target String
	 * @param Target the target
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> setTarget(String Target)
	{
		this.toModify.target(Target);
		return this;
	}
	
	/**
	 * Method used to set an optional mapper
	 * @param f the mapper function
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> setMapper(Function<T,?> f)
	{
		this.toModify.mapper(f);
		return this;
	}
	
	/**
	 * Method used to set a parser per parser identifier
	 * @param chosenParser the chosen parser to be found in ParserFactory as a static int
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> setParser(int chosenParser)
	{
		try {
			this.toModify.Parser(this.PF.chooseParserFunction(chosenParser));
			return this;
		} catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Method used to set a validator per identifier without additional parameters
	 * @param ValidatorNum the chosen validator identifier
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> setValidator(int ValidatorNum)	
	{
		try
		{
			double[] d = new double[0];
			
			this.setValidator(ValidatorNum, d);
			return this;
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Method used to set a chosen Validator per Identifier(In ValidatorFactory as a static int) with a single parameter
	 * @param chosenValidator the identifier
	 * @param singleP the parameter to be compared against
	 * @return this for chain building
	 */
	public HandlerFactory<T> setValidator(int chosenValidator, int singleP)
	{
		try
		{
			this.toModify.Validator
					(this.VF.choosePredicate(chosenValidator, singleP));
			return this;
		} catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Method used to set a chosen Validator per Identifier(In ValidatorFactory as a static int) with multiple parameters
	 * @param chosenValidator the identifie
	 * @param params thhe parameters to compare against as a double array
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> setValidator(int chosenValidator, double[] params) 
	{
		try
		{
			this.toModify.Validator(this.VF.choosePredicate(chosenValidator, params));
			return this;
		} catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * sets the userprompt
	 * @param s theh question you want the user to answer
	 * @return this for chain building
	 */
	public HandlerFactory<T> setUserprompt(String s)
	{
		toModify.Userprompt(s);
		return this;
	}
	
	/**
	 * sets the input container to a new one if given one used to specify input type (is called out of parser factory)
	 * @param i the container
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> setInput(GenericContainer<T> i)
	{
		this.toModify.inputContainer(i);
		return this;
	}	
	
	/**
	 * sets the BufferedReader
	 * @param br for user input
	 * @return this for chainbuilding
	 */
	public HandlerFactory<T> setReader(BufferedReader br)
	{
		this.toModify.Reader(br);
		return this;
	}
	
	/**
	 * Method used to build the specified object and to check if you have set all neccessary parameters
	 * @return the new InputHandler
	 */
	@SuppressWarnings("unchecked")
	public A_InputHandler<T> build()
	{
		try
		{
			A_InputHandler<T> tmp = (A_InputHandler<T>) toModify.clone();
			this.chooseHandlerType(chosenHandler);		
			if(tmp == null)
				throw new SomethingWentWrongAndIDontKnowWhat
							("IDK maan");			
			if(	tmp.isNotComplete())
			{				
				throw new IncompleteHandler
							(tmp.getUncompleteMessage());
			}
			return tmp;
		} catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * method used to close the factory
	 */
	@Deprecated
	public void close()
	{
		this.toModify = null;
	}
}

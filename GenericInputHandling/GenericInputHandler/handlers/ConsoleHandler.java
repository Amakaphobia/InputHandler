package handlers;

import java.io.IOException;

import boxes.GenericContainer;
import exceptions.BrokenRule;
import exceptions.OutOfBounds;
import logic.A_InputHandler;

/**
 * Handler used for user interaction with the console
 * @author Dave
 *
 * @param <T> the expected type
 */
public class ConsoleHandler<T> extends A_InputHandler<T>
{
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleInput()
	{
		while(true)
		{
			System.out.println(this.Userprompt);			
			try
			{
				T O = this.Parser.apply(
						 this.br.readLine());
				
				if(!this.tryInput(O))	
				{	
					
					if(this.Validator.hasbounds())
						throw new OutOfBounds(this.getBoundaryRule());
					throw new BrokenRule
							("Your Input violates the rules. Please try again");
				}
				if(this.mapper != null)
					O = (T) this.mapper.apply(O);
				this.input = new GenericContainer<>(O);
				break;
			}
			catch (OutOfBounds e)	{System.out.println(e.getMessage());}
			catch (NumberFormatException e)	{System.out.println("That wasnt the right format plz read text.");}
			catch (BrokenRule e)	{System.out.println(e.getMessage());} 
			catch (IOException e)				{e.printStackTrace();break;	}
		}		
	}
	
	@Override
	public boolean isNotComplete() 
	{
		return this.br == null||this.Parser == null||this.Validator == null;
	}

	@Override
	public ConsoleHandler<T> getHandler() 
	{
		return this;
	}

	@Override
	public String getUncompleteMessage() {
		StringBuilder strb = new StringBuilder("You Failed to provide: ");
		if(this.getParser() == null)
			strb.append("Parser, ");
		if(this.getValidatorBoundary() == null)
			strb.append("Predicate, ");
		if(this.getReader() == null)
			strb.append("Reader, ");
		strb.deleteCharAt(strb.length()-2);
		strb.append('.');
		return strb.toString();
	}
	
	@Override
	public A_InputHandler<?> target(String s)
	{
		this.unsupportedOp();
		return this;
	}

}

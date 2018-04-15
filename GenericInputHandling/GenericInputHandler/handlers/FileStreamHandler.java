package handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.function.Function;

import boxes.GenericContainer;
import logic.A_InputHandler;
import logic.ValidatorBoundary;

/**
 * Class used to read from a file
 * @author Dave
 * @param <T> input type
 */
public class FileStreamHandler<T> extends A_InputHandler<T> 
{

	

	@Override
	public boolean isNotComplete() {
		return this.getTarget().isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleInput() {
		
		File F = new File(this.target);
		
		System.out.println("---------------------");
		System.out.println("Reading Input File: ");
		System.out.println(F.getAbsolutePath());
		
		if(!this.Userprompt.isEmpty())			
			System.out.println(this.Userprompt);
		
		try
		{
			this.br = new BufferedReader(new FileReader(F));
			T O = (T) this.getReader().lines();
			this.input = new GenericContainer<>(O);		
		}
		catch(Exception e)		{e.printStackTrace();}
		
		System.out.println("Done Reading File");
		System.out.println("---------------------");
	}

	@Override
	public String getUncompleteMessage() {
		StringBuilder strb = new StringBuilder("You Failed to provide: ");
		if(this.getTarget().isEmpty())
			strb.append("Target.");
		return strb.toString();
	}
	
	/**
	 * Used to close the reader
	 */
	public void closeIt()
	{
		try
		{
			this.getReader().close();
		}
		catch(Exception e) {e.printStackTrace();};
	}
	
	@Override
	public A_InputHandler<T> Parser(Function<String, T> p)
	{
		this.unsupportedOp();
		return this;
	}
	
	@Override
	public A_InputHandler<T> Validator(ValidatorBoundary<T> p)
	{
		this.unsupportedOp();
		return this;
	}
	
	@Override 
	public A_InputHandler<T> Reader(BufferedReader br)
	{
		this.unsupportedOp();
		return this;
	}
}

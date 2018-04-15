package handlers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import javax.imageio.ImageIO;

import boxes.GenericContainer;
import exceptions.WrongRuleCode;
import logic.A_InputHandler;
import logic.ValidatorBoundary;
/**
 * Class used to import  bunch of images from a single folder
 * @author Dave
 *
 * @param <T> the input type
 */
public class ImagesByFolder<T> extends A_InputHandler<T> {

	@Override
	public String getUncompleteMessage() {
		StringBuilder strb = new StringBuilder("You Failed to provide: ");
		if(this.getTarget().isEmpty())
			strb.append("Target.");
		return strb.toString();
	}

	@Override
	public boolean isNotComplete() {
		return this.target.isEmpty();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void handleInput() 
	{
		File F = new File(this.target);
		LinkedList<BufferedImage> list = new LinkedList<>();
		try
		{
			if(!F.isDirectory())
				throw new WrongRuleCode("This is no Folder");
			if(!F.exists())
				throw new WrongRuleCode("This Folder doesnt Exist");
			List<File> ImageList = Arrays.asList(F.listFiles());
			ImageList.stream()
			.filter(File -> File.isFile())
			.map(File -> this.getPictureFromFile(File))
			.forEach(list::add);
			
			this.input = new GenericContainer<T>((T) list);
		}
		catch(Exception e) {e.printStackTrace();}
	}

	/**
	 * Gets a image given a file
	 * @param f file contaaining image
	 * @return the image
	 */
	private BufferedImage getPictureFromFile(File f)
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(f);
		}catch(Exception e)	
		{
			e.printStackTrace();
		}
		return img;
	}
	
	@Override
	public A_InputHandler<T> getHandler() {return this;}

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

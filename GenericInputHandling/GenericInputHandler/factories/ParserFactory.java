package factories;

import java.util.function.Function;

import exceptions.DatatypeNotImplemented;

/**
 * This Class is Factory for parsers to put the input into the right dataformat
 * @author Dave
 * @param <T> return type of the parser
 *
 */
public class ParserFactory <T>
{
	/**Identifies PARSE_TO_STRING */
	public static final int PARSE_TO_STRING = 0;
	/**Identifies PARSE_TO_INTEGER*/
	public static final int PARSE_TO_INTEGER = 1;
	/**Identifies PARSE_TO_DOUBLE*/
	public static final int PARSE_TO_DOUBLE = 2;
	/**Identifies PARSE_TO_FLOAT */
	public static final int PARSE_TO_FLOAT = 3;
	/**Identifies PARSE_TO_LONG*/
	public static final int PARSE_TO_LONG = 4;
	/**Identifies PARSE_TO_SHORT*/
	public static final int PARSE_TO_SHORT = 5;
	
	/**
	 * Given a parserIdentifier it will return a parsing function
	 * @param parserNum the parser identifier found in ParserFactory.PARSE_TO_...
	 * @return the parsing function
	 * @throws Exception if there is no such parser specified
	 */
	@SuppressWarnings("unchecked")
	public Function<String, T> chooseParserFunction(int parserNum) throws Exception
	{
		Function<String, ?> f = null;
		
		switch(parserNum)
		{
			case PARSE_TO_STRING:
				f = e -> e;
				break;
			case PARSE_TO_INTEGER:
				f = Integer::parseInt;
				break;
			case PARSE_TO_DOUBLE:
				f = Double::parseDouble;
				break;
			case PARSE_TO_FLOAT:
				f = Float::parseFloat;
				break;
			case PARSE_TO_LONG:
				f = Long::parseLong;
				break;
			case PARSE_TO_SHORT:
				f = Short::parseShort;
				break;
			default:
				throw new DatatypeNotImplemented("Datatype not implemented yet");
		}

		return (Function<String, T>) f;
	}
}

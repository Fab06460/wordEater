package wordEater;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * This little program reads a text and generates on the system console a report showing the occurrence 
 * of each word in the text, primarily sorted by word length, and secondary sorted by ASCII value of the word.
 * 
 * The text can be provided either by specifying a file name as the argument of the command
 * or through stdin (example: ./wordEaterMain < theText.txt)
 * 
 * @author fabricebelloncle
 * @version 1.0
 * 
 * <LICENSE TEXT HERE>
 * 
 */
public class wordEaterMain {
	private final static Logger log = Logger.getLogger(wordEaterMain.class.getName());
	
	public static void main(String[] args) {		
		log.info("starting");
		
		// There can be one command line argument specifying the file name to read
		// If no file name specified, the command will read from System.in (stdin)
		if (args.length > 1 ) {
			String message = "Too many arguments: you should specify one file name containing the text to be analyzed"; 
			System.out.println(message);
			log.severe(message);
			System.exit(-1);
		}
		InputStream is = System.in;
		if ( args.length == 1 ) {
			String filename = args[0];
			try {
				is = new FileInputStream(filename);
			} catch (FileNotFoundException e) {
				String message = "file with name '" + filename + "' not found";
				log.severe(message);
				System.out.println(message);
				System.exit(-1);
			}
			log.fine("reading text from file '" + filename + "'");
		}
		else {
			log.fine("reading text from stdin");
		}
		
		Analyzer analyzer = new Analyzer();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String s;
		try {
			// Read lines from buffer
			while ((s = in.readLine()) != null && s.length() != 0) {
				log.finest("read line: " + s);
				analyzer.addString(s);
			}
		} catch (IOException e) {
			String message = "IOException while reading stdin: " + e.toString();
			System.out.println(message);
			log.severe(message);
		}

		// Output the generated report
		System.out.println(analyzer.generateReport());		
	}
}

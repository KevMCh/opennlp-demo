/**
 * Example to use this project.
 */

package org.kevin.myexample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Example {
	
	/**
	 * Read file
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readFile(String fileName) throws FileNotFoundException, IOException {        
        String string;
        String finalString = "";
        
        FileReader f = new FileReader(System.getProperty("user.dir") + "/files/" + fileName);
        BufferedReader b = new BufferedReader(f);
        while((string = b.readLine()) != null) {
        	finalString += string; 
        }
        b.close();
        
        return finalString;
    }
	
	/**
	 * Tokenizer function
	 * @param text
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String[] tokenizer(String text) throws FileNotFoundException {
		String[] tokens = null;
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try {
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			
			if(text != ""){
				tokens = tokenizer.tokenize(text);
			}
			
			
		} catch( IOException e ) {
			e.printStackTrace();
		
		} finally {
			if(modelIn != null) {
				try {
					modelIn.close();
				}
				catch( IOException e ) {}
			}
		}
		
		return tokens;
	}
	
	/**
	 * Main class
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
				
		if(args.length <= 0) {
			System.out.println("Error, you have to define a filename");
			
		} else {
			
			String fileName = args[0];
			String tokenizerString = readFile(fileName);
			String[] tokens = tokenizer(tokenizerString);
					
			for(String token : tokens) {
				System.out.println(token);
			}
		}
	}
}

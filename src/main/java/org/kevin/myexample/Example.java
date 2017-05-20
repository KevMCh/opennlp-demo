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

	public static void main(String[] args) throws Exception {
				
		if(args.length <= 0) {
			System.out.println("Error, you have to define a filename");
			
		} else {
			
			String fileName = args[0];
			String tokenizerString = readFile(fileName);
					
			InputStream modelIn = new FileInputStream( "models/en-token.model" );
			
			try {
				TokenizerModel model = new TokenizerModel(modelIn);
				Tokenizer tokenizer = new TokenizerME(model);
				
				if(tokenizerString != ""){
					String[] tokens = tokenizer.tokenize(tokenizerString);
					
					for(String token : tokens) {
						System.out.println(token);
					}
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
		}
	}
}

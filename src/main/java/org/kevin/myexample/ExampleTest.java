package org.kevin.myexample;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.*;

public class ExampleTest {
	
	public String filename;
	
	@Before
	public void defineFilename(){
		setFilename("example.txt");
	}
 
	@Test
	public void readAFile() {
		String text = null;
		
		Example example = new Example();
		
		try {
			text = example.readFile(getFilename());
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		assertNotNull(text);
		assertTrue(text != "");
	}
	
	@Test
	public void createTokens() {
		String text = null;
		String[] tokens = null;
		
		Example example = new Example();
		
		try {
			text = example.readFile(getFilename());
			tokens = example.tokenizer(text);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		assertNotNull(tokens);
		assertTrue(tokens.length != 0);
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
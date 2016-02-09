package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Summary {
	private static String outputFile="";
	
	public static String getOutputFile(){
		return Summary.outputFile;
	}
	public static void setOutputFile(String file){
		Summary.outputFile = file;
	}
	
	public static void writeToOutputFile(String text){
		try(PrintWriter output = new PrintWriter(new FileWriter(Summary.getOutputFile(),true))) 
		{
		    output.printf("%s\r\n", text);
		} catch (IOException e) {
		}
	}
}

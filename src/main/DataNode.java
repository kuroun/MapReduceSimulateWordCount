package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataNode {
	public ArrayList<String> readFile(String file){
		String fileName = file;
		String line = null;
		ArrayList<String> result = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null){
				String[] tokens = line.split(" ");
				//System.out.println(tokens.length);
				for(int i = 0; i< tokens.length; i++){
					result.add(tokens[i]);
				}
				//result.add(line);
			}
					
		}
		catch(FileNotFoundException ex){
			System.out.println( fileName + " not found");
			
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return result;
	}
}

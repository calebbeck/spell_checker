// Written By:  	Caleb Beck
// Date:  			9/10/21
// Program Name:	spellChecker.java

package spell_checker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class spellChecker {

	public static void main(String[] args) throws FileNotFoundException {
		//Create files from the arguments given with the application.
		File dictionaryFile = new File(args[0]);
	    File uncheckedFile = new File(args[1]);
	    
	    //Process both of the files and populate ArrayLists with their contents.
	    ArrayList<String> parsedDictionary = populateDictionary(dictionaryFile);
	    ArrayList<String> fileContents = populateUncheckedFile(uncheckedFile);
	    
	    //Compare the file to be checked against the dictionary file to determine spelling errors.
	    ArrayList<String> spellingErrors = compareLists(parsedDictionary,fileContents);
	    
	    System.out.println("The following words do not exist in the dictionary:  ");
	    for (String s : spellingErrors) {
	    	System.out.println("\t" + s);
	    }
	}

	public static ArrayList<String> populateDictionary(File dictionaryFile) throws FileNotFoundException {
		ArrayList<String> completedDictionary = new ArrayList<String>();
		
		Scanner dictionaryReader = new Scanner(dictionaryFile);
		
		//While the dictionary file has an unread line, read that line and populate the dictionaryFile.
		while (dictionaryReader.hasNext()) {
			String currentEntry = dictionaryReader.next();
			
			//If the current line does not exist in the dictionary file, add the current line.
			if (! completedDictionary.contains(currentEntry)) {
				completedDictionary.add(currentEntry);
			}
		}
		dictionaryReader.close();
		return completedDictionary;
	}
	
	public static ArrayList<String> compareLists(ArrayList<String> dictionary,ArrayList<String> unchecked){
		ArrayList<String> spellingErrors = new ArrayList<String>();
		
		for (String word : unchecked) {
			if (! dictionary.contains(word)) {
				spellingErrors.add(word);
			}
		}
		return spellingErrors;
	}
	
	public static ArrayList<String> populateUncheckedFile(File uncheckedFile) throws FileNotFoundException{
		ArrayList<String> fileContents = new ArrayList<String>();
		Scanner fileReader = new Scanner(uncheckedFile);
		
		while(fileReader.hasNext()) {
			String currentEntry = fileReader.next();
			fileContents.add(currentEntry);
		}
		
		fileReader.close();
		return fileContents;
	}
}

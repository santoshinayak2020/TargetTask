import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFileByRegex {
	public List<Integer> searchString(String fileName, 
            String phrase) throws IOException{
			Scanner fileScanner = new Scanner(new File(fileName));
			int lineID = 0;
			List<Integer> lineNumbers = new ArrayList();
			Pattern pattern =  Pattern.compile(phrase);
			Matcher matcher = null;
			while(fileScanner.hasNextLine()){
			String line = fileScanner.nextLine();
			lineID++;
			matcher = pattern.matcher(line);
			if(matcher.find()){
			lineNumbers.add(lineID);
			
			}
			
			
			}
			return lineNumbers;
	}

}

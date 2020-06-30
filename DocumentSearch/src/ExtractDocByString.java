import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExtractDocByString {
	
	public static void main(String[] args) throws IOException { 
		 final String file = "Student.txt";
	        String line = null;
	        ArrayList<String> fileContents = new ArrayList<>();

	        try {
	            FileReader fReader = new FileReader(file);
	            BufferedReader fileBuff = new BufferedReader(fReader);
	            while ((line = fileBuff.readLine()) != null) {
	                fileContents.add(line);
	            }
	            fileBuff.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        System.out.println(fileContents.contains("Mark Sagal"));
	}

}

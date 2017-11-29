package simpleregex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegex {

    public class Match{
    
        public List<String> findMatches(File file, String ptrn){
            List<String> arr = new ArrayList<>();
            Pattern pattern = Pattern.compile(ptrn);
            Matcher matcher;

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                if(line != null){

                    while(line != null){
                        matcher = pattern.matcher(line);
                        boolean found = matcher.find();

                        if(found){
                            arr.add(line);
                        }

                        line = br.readLine();
                    }

                }else{
                    System.out.println("The file is empty");
                }

            } catch (FileNotFoundException ex) {
                System.out.println("FileNotFound!");
            } catch (IOException ex) {
                System.out.println("IOException!");
            }

            return arr;
        }

    }

    public static void main(String[] args) {
        
        File file = new File("d:\\test.txt");

        //pattern1 allows preceding/following text to join an even number, i.e. "text.10" or "10.text"
        String pattern1 = "(?<=(^|[^\\w.]|(\\D\\.)))(?=\\d*[02468]($|[^.\\w]|(\\.\\D)))\\d*[02468]";
        //pattern2 is more strict and looks only for even numbers not surrounded with periods
        String pattern2 = "(?<=(^|[^\\w.]))(?=\\d*[02468]($|[^.\\w]))\\d*[02468]";

        SimpleRegex.Match regEx = new SimpleRegex().new Match();
        List<String> result = regEx.findMatches(file, pattern2);

        for(String line : result){
            System.out.println(line);
        }
        
    }
    
}

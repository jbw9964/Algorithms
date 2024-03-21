import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String inputString = br.readLine();
        
        boolean is_java = inputString.indexOf('_') == -1 ? true : false;
        char letter = inputString.charAt(0);
        boolean is_valid = 'A' <= letter && letter <= 'Z' ? false : letter == '_' ? false : true;

        if (is_java && is_valid) {  /*  java --> C++
            first letter should be lowercase
            */

            for (int i = 0; i < inputString.length(); i++) {
                letter = inputString.charAt(i);

                if ('A' <= letter && letter <= 'Z') sb.append("_").append((char) (letter - 'A' + 'a'));
                else                                sb.append(letter);
            }
        }
        else if (is_valid) {        /*  C++ --> java
            no uppercase should be exist
            first letter only should be lowercase
            last letter should be alphabet
            in string, no "__" substring exist
            */

            if (!inputString.toLowerCase().equals(inputString))             is_valid = false;
            else if (inputString.indexOf("__") != -1)                       is_valid = false;
            else if (inputString.charAt(inputString.length() - 1) == '_')   is_valid = false;
            else {
                for (String word : inputString.split("_")) {
                    sb.append(word.substring(0, 1).toUpperCase() + word.substring(1));
                }
                
                String temp = sb.toString();
                sb = new StringBuilder(
                    temp.substring(0, 1).toLowerCase() + temp.substring(1)
                );
            }
        }

        System.out.println(is_valid ? sb.toString() : "Error!");
    }
}
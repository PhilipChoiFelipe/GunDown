/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minsgame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
For Utility!!
* translates the text files into the number! 
 */
public class Utils {
    
    //creates the instance Builder and append each line of text file into Builder. 
    public String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();
        InputStream is = getClass().getResourceAsStream(path);
        InputStreamReader isr = new InputStreamReader(is);
        try{
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null)
                builder.append(line +"\n");
            
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return builder.toString();
    
    }
    
    //parse the string value into Integer. 
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}

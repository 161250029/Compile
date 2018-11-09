package tool;

import entity.Token;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTool {
    public static List<Character> getInputFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "GBK"));
        ArrayList<Character> input = new ArrayList<Character>();
        String str = " ";
        while ((str = br.readLine()) != null) {
            for (int i = 0; i < str.length(); i++) {
                input.add(str.charAt(i));
            }
            input.add('\n');
        }
        input.add('$');
        br.close();
        return input;
    }

    public static void getOutput(List<Token> tokenList , String path) throws IOException {
           BufferedWriter writer = new BufferedWriter(new FileWriter(path));
           for(Token t : tokenList) {
               writer.write(t.toString());
               writer.newLine();
           }
           writer.close();
    }

    public static String toCString(ArrayList<Character> characters) {
        String result = "";
        for(char c : characters) {
            result = result + c;
        }
        return result;
    }

    /*
调试
 */
//    public static void main(String[] args) throws IOException {
//        System.out.println(getInputFile("input_test.txt"));
//    }
}

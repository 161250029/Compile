import entity.FormError;
import entity.OverError;
import entity.Token;
import entity.UndefineError;

import tool.FileTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Analyzer {
    private int state;
    private ArrayList<Token> tokenArrayList;
    public static String[] reservedWords = new String[]{
            "class", "public", "protected", "private", "void", "static", "int", "char", "float",
            "double", "String", "if", "else", "switch", "case", "for", "do", "while", "try", "catch" ,"override"
    };

    public Analyzer() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ArrayList<Token> getTokenArrayList() {
        return tokenArrayList;
    }

    public void setTokenArrayList(ArrayList<Token> tokenArrayList) {
        this.tokenArrayList = tokenArrayList;
    }

    public void start(String path) throws IOException {
        List<Character> newlist = FileTool.getInputFile(path);
        System.out.println(newlist.size());
        tokenArrayList = new ArrayList<Token>();
        int index = 0;                                //输入流索引
        int row = 1;                                  //定位程序行数
        while(newlist.get(index) != '$') {
            String reservewords = "";
            int number = 0;
            ArrayList<Character> variables = new ArrayList<Character>();
            char c = newlist.get(index);
            boolean overflag = false;
            if(c<= '9' && c>= '0') {
                while(c>='0'&& c<= '9') {
                    number = number*10 + c - '0';
                    if(number < 0 ) {
                        state = -2;
                        overflag = true;
                        //return;
                    }
                    c = newlist.get(++index);
                }
                if(overflag) {
                    state = -2;
                }
                boolean errorflag = false;
                while((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    c = newlist.get(++index);
                    errorflag = true;
                }
                if(errorflag) {
                    state = -10;        //一种处理机制，非法变量
                }
                if(!errorflag && !overflag) {
                    state = 57;
                }
                //index -=1;
            }
            else if(('a'<=c && c<= 'z') || ('A'<=c && c <= 'Z')) {         //reserverwords  or   variables
                 while((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                      variables.add(c);
                      c = newlist.get(++index);
                 }
                 boolean flag = true;
                 for(int i = 0 ; i < reservedWords.length; i++) {
                     if(FileTool.toCString(variables).equals(reservedWords[i])) {
                           state = i+1;
                           reservewords = reservedWords[i];
                           flag = false;
                     }
                 }
                 if (flag) {
                     state = 56;
                 }

            }
            else {
                variables.add(c);
                 switch (c) {
                     case '=' :
                         c = newlist.get(++index);
                         if(c == '=') {
                             state = 31;
                             variables.add(c);
                             index = index + 1;
                             break;
                         }
                         else {
                             state = 30;
                             break;
                         }
                     case '+' :
                         c = newlist.get(++index);
                         if(c == '=') {
                             variables.add(c);
                             state = 23;
                             index += 1;
                             break;
                         }
                         else {
                             state = 22;
                             break;
                         }
                     case '*' :
                         c = newlist.get(++index);
                         if (c == '=') {
                             variables.add(c);
                             state = 27;
                             index += 1;
                             break;
                         }
                         else {
                             state = 26;
                             break;
                         }
                     case '/' :
                         c = newlist.get(++index);
                         if(c == '=')  {
                             variables.add(c);
                             state = 29;
                             index += 1;
                             break;
                         }
                         else {
                             state = 28;
                             break;
                         }
                     case '&' :
                         c = newlist.get(++index);
                         if (c == '&') {
                             variables.add(c);
                             state = 33;
                             index += 1;
                             break;
                         }
                         else {
                             state = 32;
                             break;
                         }
                     case '|' :
                         c = newlist.get(++index);
                         if (c == '|') {
                             variables.add(c);
                             state = 35;
                             index += 1;
                             break;
                         }
                         else {
                             state = 34;
                             break;
                         }
                     case '!' :
                         c = newlist.get(++index);
                         if (c == '=') {
                             variables.add(c);
                             state = 37;
                             index += 1;
                             break;
                         }
                         else {
                             state = 36;
                             break;
                         }
                     case '<' :
                         c = newlist.get(++index);
                         if(c == '=') {
                             variables.add(c);
                             state = 39;
                             index += 1;
                             break;
                         }
                         else {
                             state = 38;
                             break;
                         }
                     case '>' :
                         c = newlist.get(++index);
                         if(c == '=') {
                             variables.add(c);
                             state = 41;
                             index += 1;
                             break;
                         }
                         else {
                             state = 40;
                             break;
                         }
                     case '(' :
                             state = 45;
                             index += 1;
                             break;
                     case ')' :
                             state = 46;
                             index += 1;
                             break;
                     case '[' :
                             state = 47;
                             index += 1;
                             break;
                     case ']':
                         state = 48;
                         index += 1;
                         break;

                     case '{':
                         state = 49;
                         index += 1;
                         break;

                     case '}':
                         state = 50;
                         index += 1;
                         break;

                     case ',':
                         state = 51;
                         index += 1;
                         break;

                     case ':':
                         state = 52;
                         index += 1;
                         break;

                     case ';':
                         state = 53;
                         index += 1;
                         break;

                     case '\'':
                         state = 54;
                         index += 1;
                         break;

                     case '\"':
                         state = 55;
                         index += 1;
                         break;

                     case '\n':
                         state = -1;
                         index += 1;
                         break;

                     case ' ':
                         state = -4;
                         index += 1;
                         break;

                     case '-':
                         c = newlist.get(++index);
                         if (c >= '0' && c <= '9') { //negative number
                             boolean flag = false;
                             while (c >= '0' && c <= '9') {
                                 number = number * 10 + c - '0';
                                 c = newlist.get(++index);

                                 //ERROR: number is too large
                                 if (number < 0) {
                                     flag = true;
                                     state = -2;
                                 }

                                 state = 57;
                             }
                             if(flag) {
                                 state = -2;
                             }
                             else {
                                 state = 57;
                             }
                             number *= -1;
                         } else if (c == '=') { // -=
                             variables.add(c);
                             state = 25;
                             index += 1;
                         } else { // -
                             state = 24;
                             index--;
                         }
                         break;


                     default:
                         state = -3;
                         index += 1;
                         break;
                 }
            }
          switch (state) {
              case 57:
                  String content = Integer.toString(number);
                  tokenArrayList.add(new Token(state , content));
                  break;
              case -1:
                  row++;
                  break;
              case -2:
                  String result = Integer.toString(number);
                  tokenArrayList.add(new Token(state ,result , new OverError(row)));
                  break;
              case -3:
                  tokenArrayList.add(new Token(state , FileTool.toCString(variables) , new UndefineError(row)));
                  break;
              case -4:
                  break;
              case -10:
                  tokenArrayList.add(new Token(state , "error" ,new FormError(row)));
              default:
                  String str = FileTool.toCString(variables);
                  tokenArrayList.add(new Token(state, str));
                  break;
          }
        }
    }

    public void printOutput(String path) throws IOException {
        FileTool.getOutput(tokenArrayList , path);
    }

    public static void main(String[] args) throws IOException {
        Analyzer a = new Analyzer();
        a.start("input_test.txt");
        a.printOutput("output_result.txt");
    }
}

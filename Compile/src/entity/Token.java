package entity;

public class Token {
    int state;
    String content;
    MyError error;
    public Token(int state , String content , MyError error) {
        this.state = state;
        this.content = content;
        this.error = error;
    }

    public Token(int state, String content) {
        this.state = state;
        this.content = content;
        this.error = null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyError getError() {
        return error;
    }

    public void setError(MyError error) {
        this.error = error;
    }

    public  String toString() {
        String result = "";
        if (error != null) {
            result = error.toString();
        }
        else {
            result = "(" + state + "," + content+ ")";
        }
        return result;
    }
/*
调试
 */
//    public static void main(String[] args) {
//        Token t = new Token(15 , "hhhh" , new OverError(10));
//        System.out.println(t.getError().toString());
//    }
}

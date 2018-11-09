package entity;

public class UndefineError extends MyError {
       public UndefineError(int row) {
           super("ERROR: You input String Undefined" , row);
       }
}

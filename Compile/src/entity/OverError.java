package entity;

public class OverError extends MyError {
     public OverError(int row) {
         super("ERROR: You input number is Too Large" , row );
     }
}

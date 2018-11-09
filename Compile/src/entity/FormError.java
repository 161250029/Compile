package entity;

public class FormError extends MyError {
    public FormError(int row) {
        super("ERROR: You variable form is error" , row);
    }
}

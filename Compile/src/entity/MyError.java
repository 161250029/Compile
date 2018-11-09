package entity;

public class MyError {
    protected String error ;
    protected int row;

    public MyError(String error, int row) {
        this.error = error;
        this.row = row;
    }

    public MyError() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String toString() {
        String result = "";
        result = error + "       出错位置在" + "第" + row + "行";
        return result;
    }
}

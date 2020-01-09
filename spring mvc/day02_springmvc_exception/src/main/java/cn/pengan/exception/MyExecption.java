package cn.pengan.exception;

public class MyExecption extends Exception  {
    private String message;

    public MyExecption(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

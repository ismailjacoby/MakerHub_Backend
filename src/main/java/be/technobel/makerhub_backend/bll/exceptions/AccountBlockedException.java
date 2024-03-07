package be.technobel.makerhub_backend.bll.exceptions;

public class AccountBlockedException extends RuntimeException{
    public AccountBlockedException(String message){
        super(message);
    }
}

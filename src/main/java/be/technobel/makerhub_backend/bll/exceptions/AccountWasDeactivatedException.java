package be.technobel.makerhub_backend.bll.exceptions;



public class AccountWasDeactivatedException extends RuntimeException{
    public AccountWasDeactivatedException(String message){
        super(message);
    }
}
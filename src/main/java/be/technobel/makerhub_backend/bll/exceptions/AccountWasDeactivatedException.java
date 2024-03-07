package be.technobel.makerhub_backend.bll.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Your account was deactivated. Please contact support to reactivate your account.")
public class AccountWasDeactivatedException extends RuntimeException{
    public AccountWasDeactivatedException(String message){
        super(message);
    }
}
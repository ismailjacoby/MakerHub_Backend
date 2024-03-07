package be.technobel.makerhub_backend.bll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Your Account is blocked. Please contact support to reactivate your account.")
public class AccountBlockedException extends RuntimeException{
    public AccountBlockedException(String message){
        super(message);
    }
}

package com.miguelmallqui.studentenrollmen.validators;

import lombok.Data;

@Data
public class Validators {
    public boolean validateEmail(String email){
        if(!email.contains("@")){
            return false;
        }
        if(!email.contains(".")){
            return false;
        }
        if(email.endsWith("@")){
            return false;
        }
        if(email.contains(" ")){
            return false;
        }
        if(email.length()> 255){
            return false;
        }
        return true;
    }
}

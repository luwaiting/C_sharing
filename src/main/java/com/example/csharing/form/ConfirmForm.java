package com.example.csharing.form;

public class ConfirmForm {
    private String confirmCode;

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }
    public boolean confirm(String token){
        if(this.confirmCode.equals(token)){
            return true;
        }
        return false;
    }
}

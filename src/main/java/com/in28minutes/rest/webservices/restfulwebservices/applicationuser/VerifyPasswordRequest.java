package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

public class VerifyPasswordRequest {
    String password;
 
    public VerifyPasswordRequest(String password) {
        this.password = password;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}

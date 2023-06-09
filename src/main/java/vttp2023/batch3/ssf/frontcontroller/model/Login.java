package vttp2023.batch3.ssf.frontcontroller.model;

import jakarta.validation.constraints.Size;

public class Login {
    @Size(min=2, message="must be at least 2 characters")
    private String username;
    @Size(min=2, message="must be at least 2 characters")
    private String password;
    private boolean authenticated;
    private int attempts;
    
    public Login() {

    }
    
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.attempts = 0;
    }

    public String getUsername() {
        return username;
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    public int getAttempts() {
        return attempts;
    }
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int failedattempt(){
        return this.attempts = attempts + 1;
    }
    @Override
    public String toString() {
        return "Login [username=" + username + ", password=" + password + ", authenticated=" + authenticated
                + ", attempts=" + attempts + "]";
    }

    
    
}

package com.yaroma;

import com.yaroma.equilibrium.model.User;

public class SessionBean {
    
    private User currentUser;
    
    public SessionBean(){
        
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    
    
}

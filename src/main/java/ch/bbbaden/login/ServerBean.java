package ch.bbbaden.login;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.IOException;
import org.jdom2.JDOMException;

@Named(value = "serverBean")
@SessionScoped
public class ServerBean implements Serializable {

    private String username = "";
    private String password = "";
    private LoginDAO login = new LoginDAO();
    private boolean isLoggedIn = false;

    public ServerBean() {
    }

    public String getUsername() {
        return username;
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

    public String login() throws JDOMException, IOException {
        if (isLoggedIn()) {
            return "secured/result.xhtml";
        } else {
            return "index.xhtml";
        }
    }

    public String getSucessful() throws JDOMException, IOException {
        if (login.getPasswordByUsername(getUsername()).equals(getPassword())) {
            isLoggedIn = true;
            return "sucessful";
        } else {
            return "not authorized";
        }
    }

    public boolean isLoggedIn() throws JDOMException, IOException {
        return isLoggedIn;
    }
}

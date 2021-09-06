package ch.bbbaden.login;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

@Named(value = "loginDAO")
@SessionScoped
public class LoginDAO implements Serializable {

    public LoginDAO() {

    }

    public String getUsernameByID(String id) throws JDOMException, IOException {
        Document doc = new SAXBuilder().build("data.xml");
        Element users = doc.getRootElement();

        List list = users.getChildren("user");
        for (int i = 0; i < list.size(); i++) {
            Element node = (Element) list.get(i);
            if (node.getChild("id").equals(id)) {
                return node.getChildText("username");
            }
        }
        return "user not found";
    }

    public String getPasswordByID(String id) throws JDOMException, IOException {
        Document doc = new SAXBuilder().build("data.xml");
        Element users = doc.getRootElement();

        List list = users.getChildren("user");
        for (int i = 0; i < list.size(); i++) {
            Element node = (Element) list.get(i);
            if (node.getChild("id").equals(id)) {
                return node.getChildText("password");
            }
        }
        return "user not found";
    }

    public String getPasswordByUsername(String id) throws JDOMException, IOException {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        path = path + "WEB-INF\\data.xml";

        Document doc = new SAXBuilder().build(path);
        Element users = doc.getRootElement();

        List list = users.getChildren("user");
        for (int i = 0; i < list.size(); i++) {
            Element node = (Element) list.get(i);
            if (node.getChild("username").getValue().equals(id)) {
                return node.getChildText("password");
            }
        }
        return "user not found";
    }
}

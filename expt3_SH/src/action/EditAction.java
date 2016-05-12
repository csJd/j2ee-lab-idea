package action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;

/**
 * Created by dd on 2016/5/12.
 */
public class EditAction extends ActionSupport {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

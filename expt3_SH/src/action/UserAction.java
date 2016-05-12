package action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import service.UserService;

/**
 * Created by dd on 2016/5/12.
 */
public class UserAction extends ActionSupport {
    UserService userService = new UserService();
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String addUser() {
        if (userService.add(user)) {
            addActionMessage("添加成功");
            return SUCCESS;
        } else {
            addActionError("用户名已经存在，请重新输入");
            return INPUT;
        }
    }

    public String editUser() {
        if (userService.update(user)) {
            addActionMessage("修改成功");
            return SUCCESS;
        } else {
            addActionError("用户名已经存在, 修改失败！");
            return INPUT;
        }
    }

    public String delUser() {
        if (userService.delete(user)) {
            addActionMessage("删除成功");
            return SUCCESS;
        } else {
            addActionError("删除失败");
            return SUCCESS;
        }
    }
}

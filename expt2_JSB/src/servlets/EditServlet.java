package servlets;

import beans.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dd on 2016/5/11.
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        User user = new User();
        user.setId(Integer.valueOf(req.getParameter("id")));
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));

        UserDao dao = new UserDao();
        String preUserName = dao.findUserById(user.getId()).getUsername();

        if (dao.findUserByUsername(user.getUsername()) && !user.getUsername().equals(preUserName)) {
            req.getSession().setAttribute("hint", "用户名已存在，修改失败！");
            req.getSession().setAttribute("color", "red");
            resp.sendRedirect("edit.jsp?id=" + user.getId());
        } else {
            if (dao.update(user)) {
                req.getSession().setAttribute("hint", "修改成功");
                req.getSession().setAttribute("color", "green");
            } else {
                req.getSession().setAttribute("hint", "修改失败");
                req.getSession().setAttribute("color", "red");
            }
            resp.sendRedirect("index.jsp");
        }
    }
}

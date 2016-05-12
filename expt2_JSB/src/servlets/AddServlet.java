package servlets;

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
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDao dao = new UserDao();
        if(dao.findUserByUsername(username)){
            req.getSession().setAttribute("hint", "用户名已存在，添加失败！");
            req.getSession().setAttribute("color", "red");
            resp.sendRedirect("add.jsp");
        } else {
            if (dao.add(username, password)) {
                req.getSession().setAttribute("hint", "添加成功");
                req.getSession().setAttribute("color", "green");
            } else {
                req.getSession().setAttribute("hint", "添加失败");
                req.getSession().setAttribute("color", "red");
            }
            resp.sendRedirect("index.jsp");
        }
    }
}

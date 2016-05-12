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
@WebServlet("/DelServlet")
public class DelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        UserDao dao = new UserDao();
        if (dao.delete(id)) {
            req.getSession().setAttribute("hint", "删除成功");
            req.getSession().setAttribute("color", "green");
        } else {
            req.getSession().setAttribute("hint", "删除失败");
            req.getSession().setAttribute("color", "red");
        }
        resp.sendRedirect("index.jsp");
    }
}

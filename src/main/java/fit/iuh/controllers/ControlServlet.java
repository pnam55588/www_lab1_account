package fit.iuh.controllers;

import fit.iuh.models.Account;
import fit.iuh.repositories.AccountRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;

@WebServlet(urlPatterns = "/ControlServlet")
public class ControlServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if(action.equalsIgnoreCase("login")){
            AccountRepository accountRepository = new AccountRepository();
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Account account = accountRepository.findByEmail(email);
            if(password.equalsIgnoreCase(account.getPassword())){
                session.setAttribute("account", account);
                resp.sendRedirect("pages/profile.jsp");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

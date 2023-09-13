package fit.iuh.controllers;

import fit.iuh.models.Account;
import fit.iuh.models.Log;
import fit.iuh.models.Role;
import fit.iuh.repositories.AccountRepository;
import fit.iuh.repositories.GrantAccessRepository;
import fit.iuh.repositories.LogRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

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
            LogRepository logRepository = new LogRepository();
            GrantAccessRepository grantAccessRepository = new GrantAccessRepository();

            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Account account = accountRepository.findByEmail(email);
            if(password.equalsIgnoreCase(account.getPassword())){
                Log log = new Log(1,account.getId(),null,null,"");
                logRepository.insert(log);
                Role role = grantAccessRepository.getRoleAccount(account.getId());

                session.setAttribute("role", role);
                session.setAttribute("account", account);

                if(role.getName().equalsIgnoreCase("administrator")){
                    resp.sendRedirect("dashboard.jsp");
                }else {
                    resp.sendRedirect("profile.jsp");
                }
            }
            else {
                resp.sendRedirect("index.jsp");
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if(action.equalsIgnoreCase("logout")){
            LogRepository logRepository = new LogRepository();

            Account account = (Account) session.getAttribute("account");
            session.setAttribute("account", null);
            logRepository.updateLogoutTime(account.getId(), LocalDateTime.now());

            resp.sendRedirect("index.jsp");
        }

    }
}

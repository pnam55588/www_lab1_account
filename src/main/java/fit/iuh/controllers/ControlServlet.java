package fit.iuh.controllers;

import fit.iuh.models.Account;
import fit.iuh.models.Log;
import fit.iuh.models.Role;
import fit.iuh.repositories.AccountRepository;
import fit.iuh.repositories.GrantAccessRepository;
import fit.iuh.repositories.LogRepository;
import fit.iuh.repositories.RoleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/ControlServlet")
public class ControlServlet extends HttpServlet {

    private AccountRepository accountRepository;
    private GrantAccessRepository grantAccessRepository;
    private LogRepository logRepository;
    private RoleRepository roleRepository;
    @Override
    public void init() throws ServletException {
        accountRepository = new AccountRepository();
        grantAccessRepository = new GrantAccessRepository();
        logRepository = new LogRepository();
        roleRepository = new RoleRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if(action != null){
            switch (action){
                case "login":
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
                            List<Account> accounts = accountRepository.getAll();
                            System.out.println("Number of accounts retrieved: " + accounts.size());
                            req.setAttribute("accounts", accounts);
                            req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
//                            resp.sendRedirect("dashboard.jsp");
                        }else {
                            resp.sendRedirect("profile.jsp");
                        }
                    }
                    else {
                        resp.sendRedirect("index.jsp");

                    }
                    break;
                case "test":

                    break;
                default:
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action != null) {
            switch (action) {
                case "logout":
                    LogRepository logRepository = new LogRepository();

                    Account account = (Account) session.getAttribute("account");
                    session.setAttribute("account", null);
                    logRepository.updateLogoutTime(account.getId(), LocalDateTime.now());

                    resp.sendRedirect("index.jsp");
                    break;
                case "Create Account":

                    break;
                case "edit":

                    break;
                case "delete":

                    break;
                default:
                    // Handle other actions if needed
            }
        }
    }

}

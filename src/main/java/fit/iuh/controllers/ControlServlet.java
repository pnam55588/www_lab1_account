package fit.iuh.controllers;

import fit.iuh.models.Account;
import fit.iuh.models.GrantAccess;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = "/ControlServlet")
public class ControlServlet extends HttpServlet {

    private AccountRepository accountRepository;
    private GrantAccessRepository grantAccessRepository;
    private LogRepository logRepository;

    @Override
    public void init() throws ServletException {
        accountRepository = new AccountRepository();
        grantAccessRepository = new GrantAccessRepository();
        logRepository = new LogRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if(action != null){
            switch (action){
                case "login":
                    handleLogin(req,resp);
                    break;
                case "delete":
                    handleDeleteAccount(req, resp);
                    break;
                case "edit":

                    handleEditAccount(req,resp);

                    break;
                default:
            }
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "logout":
                    handleLogout(req,resp);
                    break;
                case "Create Account":
                    handleCreateAccount(req,resp);
                    break;
                case "Save Account":
                    handleSaveAccount(req,resp);
                    break;

                default:
                    // Handle other actions if needed
            }
        }
    }

    private void handleSaveAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role");
        Account account = new Account(email,fullName,password,email,phone,1);
        account.setId(id);
        GrantAccess grantAccess = new GrantAccess(account.getId(),role,true,"");

        accountRepository.update(account);
        grantAccessRepository.update(grantAccess);

        List<Account> accounts = accountRepository.getAll();
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Account account = accountRepository.findByEmail(email);
        HttpSession session = req.getSession();

        if(password.equalsIgnoreCase(account.getPassword())){
            Log log = new Log(1,account.getId(),null,null,"");
            logRepository.insert(log);
            Role role = grantAccessRepository.getRoleAccount(account.getId());

            session.setAttribute("role", role);
            session.setAttribute("account", account);

            if(role == null || role.getName().equalsIgnoreCase("user")){
                resp.sendRedirect("profile.jsp");
            }else if(role.getName().equalsIgnoreCase("administrator")){
                List<Account> accounts = accountRepository.getAll();
                req.setAttribute("accounts", accounts);
                req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
//                            resp.sendRedirect("dashboard.jsp");
            }

        }
        else {
            resp.sendRedirect("index.jsp");

        }
    }
    private void handleLogout (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        session.setAttribute("account", null);
        logRepository.updateLogoutTime(account.getId(), LocalDateTime.now());

        resp.sendRedirect("index.jsp");
    }
    private void handleEditAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Account account = accountRepository.findById(id);
        req.setAttribute("account", account);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }
    private void handleDeleteAccount (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        if(id != null){
            accountRepository.delete(id);
        }
        List<Account> accounts = accountRepository.getAll();
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
    }
    private void handleCreateAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role");
        Account account = new Account(email,fullName,password,email,phone,1);
        GrantAccess grantAccess = new GrantAccess(account.getId(),role,true,"");
        System.out.println(account);
        System.out.println(grantAccess);
        accountRepository.insert(account);
        grantAccessRepository.insert(grantAccess);

        List<Account> accounts = accountRepository.getAll();
        req.setAttribute("accounts", accounts);
        req.getRequestDispatcher("dashboard.jsp").forward(req,resp);
    }
}

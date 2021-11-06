package pl.alex.homebudgeting.budget;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BudgetAddController extends HttpServlet {
    private final BudgetItemDao budgetItemDao = new BudgetItemDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/addform.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        BigDecimal value = new BigDecimal(request.getParameter("value"));
        BudgetItemType type = BudgetItemType.valueOf(request.getParameter("type"));
        BudgetItem item = new BudgetItem(description,value,type);
        budgetItemDao.save(item);
        response.sendRedirect(request.getContextPath()+"/");
    }
}

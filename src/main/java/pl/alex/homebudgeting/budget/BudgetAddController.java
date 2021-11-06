package pl.alex.homebudgeting.budget;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BudgetAddController extends HttpServlet {
    private final BudgetService budgetService = new BudgetService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/addform.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        BigDecimal value = new BigDecimal(request.getParameter("value"));
        BudgetItemType type = BudgetItemType.valueOf(request.getParameter("type"));
        BudgetItemDto budgetItem = new BudgetItemDto(description, value);
        if (type == BudgetItemType.EXPENSE)
            budgetService.addExpense(budgetItem);
        else if (type == BudgetItemType.INCOME)
            budgetService.addIncome(budgetItem);
        response.sendRedirect(request.getContextPath() + "/");
    }
}

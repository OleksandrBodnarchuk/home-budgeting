package pl.alex.homebudgeting.budget;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class HomeController extends HttpServlet {
    private final BudgetItemDao budgetItemDao = new BudgetItemDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BudgetItem> expenses = budgetItemDao.findAllByType(BudgetItemType.EXPENSE);
        List<BudgetItem> incomes = budgetItemDao.findAllByType(BudgetItemType.INCOME);
        BigDecimal expensesSum = getSum(expenses);
        BigDecimal incomesSum = getSum(incomes);
        BigDecimal balance = incomesSum.subtract(expensesSum);
        request.setAttribute("incomes", incomes);
        request.setAttribute("expenses", expenses);
        request.setAttribute("incomesSum", incomesSum);
        request.setAttribute("expensesSum", expensesSum);
        request.setAttribute("balance", balance);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    private BigDecimal getSum(List<BudgetItem> budgetItems) {
        return budgetItems.stream().map(BudgetItem::getValue).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }


}

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <title>Home budget</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<main>
    <h1>Home budget</h1>
    <section class="summary">
        <h2>Summary</h2>
        <p>Income: ${requestScope.incomesSum}zł</p>
        <p>Expenses: ${requestScope.expensesSum}zł</p>
        <p>Balance: ${requestScope.balance}zł</p>
    </section>
    <a href="${pageContext.request.contextPath}/add">Add expense of income</a>
    <c:if test="${not empty requestScope.incomes}">
        <section>
            <h2>Incomes</h2>
            <table>
                <thead>
                <tr>
                    <th>Description</th>
                    <th>Amount</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="income" items="${requestScope.incomes}">
                    <tr>
                        <td><c:out value="${income.description}"/></td>
                        <td>${income.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </c:if>
    <c:if test="${not empty requestScope.expenses}">
        <section>
            <h2>Expenses</h2>
            <table>
                <thead>
                <tr>
                    <th>Description</th>
                    <th>Amount</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="expense" items="${requestScope.expenses}">
                    <tr>
                        <td><c:out value="${expense.description}"/></td>
                        <td>${expense.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </c:if>
</main>
</body>
</html>
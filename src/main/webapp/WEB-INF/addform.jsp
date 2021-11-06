
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add income or expense</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<h1>New Income or Expense</h1>
<form action="${pageContext.request.contextPath}/add" method="post">
    <fieldset>
        <legend>Add new position</legend>
        <label class="keyboard-inputs">
            Income/Expense description
            <input name="description" placeholder="example: Netflix account">
        </label>
        <label class="keyboard-inputs">
            Amount(USD)
            <input name="value" type="number" step="0.01" placeholder="25.65">
        </label>
        <section>
            <label>
                Income
                <input name="type" type="radio" value="INCOME">
            </label>
            <label>
                Expense
                <input name="type" type="radio" value="EXPENSE">
            </label>
        </section>
        <button>Save</button>
    </fieldset>
</form>
</body>
</html>

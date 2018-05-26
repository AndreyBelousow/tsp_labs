<%@ page import= "task.TaskData" %>

useBean id="task" class="task.TaskData" scope="session"/>
<jsp:setProperty name="task" property="*"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logarithm - source</title>
    <link rel="stylesheet" href="./resource/html/css/main.css">
</head>
<body>

<h1>КАЛЬКУЛЯТОР ЛОГАРИФМА</h1>
<h2>Вычисляет логарифм первого аргумента по основанию второго</h2>

<form action="index.jsp" id="inputForm">
    <p>
        <label for="argument">Первый аргумент</label><br>
        <input type="number" id="argument" name="argument" placeholder="Введите аргумент"/>
    </p>
    <p>
        <label for="base">Второй аргумент</label><br>
        <input type="number" id="base" name="base" placeholder="Введите основание"/>
    </p>
    <p>
        <button class="submit" onclick="checkInputData()" type="submit" >Вычислить</button>
    </p>
</form>

<%
    request.setCharacterEncoding("UTF-8");
    double arg = Double.parseDouble(request.getParameter("argument"));
    double base = Double.parseDouble(request.getParameter("base"));
    TaskData task = new TaskData(arg, base);
    request.getSession().setAttribute("task",task);
%>

<%if (task.status()){%>
<jsp:forward page="result.jsp"/>
<%}
%>

<script>
    function checkInputData() {

        var input = document.getElementById("textField2");
        var subString = input.value;
        if (subString == "") {
            input.setCustomValidity("Строка не должна быть пустой. Введите данные.");
        } else {
            input.setCustomValidity("");
        }

        input = document.getElementById("textField1");
        var text = input.value;
        if (text == "" ) {
            input.setCustomValidity("Строка не должна быть пустой. Введите данные.");
        } else {
            input.setCustomValidity("");
        }

    }
    function proverka(input) {
        input.value = input.value.replace(/[^\d.]/g, '');
    };


</script>

</body>
</html>
<%@ page import="task.TaskData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Вычисления</title>
  <link rel="stylesheet" href="resource/style.css">
</head>
<body>

<div id="description">
  <h4>
    Данный ресурс выполняет следующую обработку вводимых данных:<br>
    Вы вводите делимое и делитель в поля, расположенные ниже.<br>
    Далее произойдет деление после нажатия на кнопку.<br>
    Результат будет показан на экране.<br>
    Так же можно скачать результат работы в виде xml файла.
  </h4>
</div>

<%
  request.setCharacterEncoding("UTF-8");
  String text = request.getParameter("Text");
  String subString = request.getParameter("SubText");
  TaskData task = new TaskData();
  if (text != null && !text.isEmpty() && subString!= null && !subString.isEmpty()) {
    task = new TaskData(text, subString);
    request.getSession().setAttribute("MyTask",task);
  }
%>

<%if (task.status()) {%>
<jsp:forward page="resource/result.html"/>
<%} %>

<form action="index.jsp" id="myForm" method="post">
  <div>
    <h4 id="text">Делимое:</h4> <input type="text" name="Text" id="textField1" placeholder=""
                                       onkeyup="return proverka(this);" onchange="return proverka(this);" ><br>
    <h4 id="text">Делитель:</h4> <input type="text" name="SubText" id="textField2" placeholder=""
                                        onkeyup="return proverka(this);" onchange="return proverka(this);" ><br>
  </div>
  <div>
    <form>
      <button type="submit" onclick="checkInputData()" name="but" id="butRes">Подтвердить</button>
    </form>
  </div>
</form>


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
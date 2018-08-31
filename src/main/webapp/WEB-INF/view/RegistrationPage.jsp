<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <script type="text/javascript" src="/js/registration.js"></script>

</head>
<body>
<form:form method="post" action="/register/add">
    <input type="text" name="Login"/>
    <button id="dupa">send </button>
</form:form>
<script>
    document.addEventListener('DOMContentLoaded', function(){
    var a = document.getElementById("dupa");
        a.addEventListener("onclick", function () {

            event.preventDefault();

            var data = $("#Login").serialize();
            $.ajax({
                url: "/register/add",
                type: "POST",
                data: data,
                error: function () {
                    console.log('Error in hiding recent transaction');
                },
                success: function () {
                    console.log('Successfully  hided recent transaction');
                }
            });
        });

    }, false);

</script>
</body>
</html>

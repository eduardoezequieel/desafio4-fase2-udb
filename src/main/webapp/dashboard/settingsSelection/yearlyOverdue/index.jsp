<%@ page import="models.UserType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Settings</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="yearlyOverdue.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container1">
            <div class="inner-container1">
                <div class="data-container2">
                    <form class="form-container">
                        <div>
                            <label for="formGroupExampleInput2" class="form-label">Año</label>
                            <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="">
                        </div>
                        <div>
                            <label for="formGroupExampleInput3" class="form-label">Tarifa</label>
                            <input type="text" class="form-control" id="formGroupExampleInput3" placeholder="">
                        </div>
                        <div class="btnUpdate">
                            <button class="primary-button" type="button">Actualizar</button>
                        </div>
                    </form>


                    <div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Año</th>
                                <th scope="col">Tarifa</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>


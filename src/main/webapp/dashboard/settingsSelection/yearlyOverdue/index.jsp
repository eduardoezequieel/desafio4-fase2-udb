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
    <div class="page-container"
         style="display: flex; flex-direction: column; align-items: center;  justify-content: center; height: 100vh;">
        <div class="inner-container1">
            <div class="inner-container1"
                 style="display: flex; flex-direction: column; align-items: center;  justify-content: center; height: 100vh;">
                <div class="data-container2" style="display: flex; center: space-between;width: 100% ; padding: 50px;">
                    <form class="form-container" style="margin: 15px">
                        <div>
                            <label for="formGroupExampleInput2" class="form-label">Año</label>
                            <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="">
                        </div>
                        <div>
                            <label for="formGroupExampleInput3" class="form-label"
                                   style="margin-top: 25px">Tarifa</label>
                            <input type="text" class="form-control" id="formGroupExampleInput3" placeholder=""
                                   style="margin-bottom: 20px; ">
                        </div>
                        <div class="btnUpdate" style="width: 50%">
                            <button class="primary-button" type="button">Actualizar</button>
                        </div>
                    </form>


                    <div>
                        <table class="table" id="usertype-table">
                            <thead>
                            <tr>
                                <th scope="col"> Mora Anual</th>
                                <th scope="col"> Año</th>

                            </tr>
                            </thead>
                            <tbody>
                            <%
                                List<Object[]> resultsList = (List<Object[]>) request.getAttribute("results");
                                for (Object[] result : resultsList) {
                                    String year = result[0].toString();
                                    String cost = result[1].toString();

                            %>
                            <tr id="row-<%=year%>">
                                <td><%= year %>
                                </td>
                                <td><%= cost %>
                                </td>
                            </tr>
                            <%
                                }
                            %>
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


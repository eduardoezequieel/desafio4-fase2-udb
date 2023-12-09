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
                                    String cost = result[0].toString();
                                    String year = result[1].toString().substring(0, 4);

                            %>
                            <tr>
                                <th scope="row"><%= year %>
                                </th>
                                <td><%= cost %>
                                </td>
                                <td>
                                    <a href="/dashboard/settingsSelection/yearlyOverdue?action=edit&year=<%=year%>&cost=<%=cost%>">
                                        <button class="primary-button" type="button">Actualizar</button>
                                    </a>

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


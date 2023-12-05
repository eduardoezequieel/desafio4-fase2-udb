<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/components/head.jsp" %>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .feedback-message {
            font-size: 20px;
        }

        .container {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            gap: 20px;
        }

        .icon-success {
            color: green;
            font-size: 50px;
            margin-right: 0;
        }

        .icon-error {
            color: red;
            font-size: 50px;
            margin-right: 0;
        }
    </style>
</head>
<body>
<div class="container text-center">
    <div class="feedback-icon">
        <%
            String messageType = (String) request.getAttribute("messageType");
            if ("success".equals(messageType)) {
        %>
        <i class="bi bi-check-circle-fill icon-success"></i>
        <%
        } else if ("error".equals(messageType)) {
        %>
        <i class="bi bi-exclamation-circle-fill icon-error"></i>
        <%
            }
        %>
    </div>

    <div class="feedback-message">
        <%= request.getAttribute("message") %>
    </div>

    <a href="<%= request.getAttribute("href") %>">
        <button class="btn btn-secondary">Continuar</button>
    </a>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>

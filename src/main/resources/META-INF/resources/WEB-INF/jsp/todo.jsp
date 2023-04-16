<%@include file="comman/header.jspf"%>
<body>
<%@include file="comman/navigation.jspf"%>
<div class="container">
    <h1>Enter Todo Details</h1>
    <%--@elvariable id="todo" type="com.in28minutes.springboot.myfirstwebapp.todo.Todo"--%>
    <form:form method="post" modelAttribute="todo">
        <fieldset class="mb-3" >
            <form:label path="description">Description</form:label>
            <form:input type="text" name="description" required="required" path="description"/>
            <form:errors path="description" cssStyle="color: red"/>
        </fieldset>
        <fieldset class="mb-3">
            <form:label path="targetDate">Target Date</form:label>
            <form:input type="text" path="targetDate" name="targetDate" required="required" />
            <form:errors path="targetDate" cssStyle="color: red"/>
        </fieldset>

        <form:input type="hidden" path="id" />
        <form:input path="done" type="hidden"/>
        <input type="submit" class="btn btn-success"/>
    </form:form>

</div>
<%@include file="comman/footer.jspf"%>
<%--<script type="text/javascript">$('#targetDate').datepicker({--%>
<%--    format:'yyyy-mm-dd'--%>
<%--})</script>--%>

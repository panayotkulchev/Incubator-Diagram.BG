<%--
  Created by IntelliJ IDEA.
  User: pepo
  Date: 07.11.16
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Product Editor</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<div class="container-fluid">
    <div class="row" style="margin-top: 30px">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Add product</h3>
                </div>
                <div class="panel-body">

                    <form:form id="productEditorForm" action="${pageContext.request.contextPath}/createProduct"
                               method="post" commandName="productForm" cssClass="form-horizontal">

                        <c:set var="codeHasError">
                            <form:errors path="code"/>
                        </c:set>
                        <c:set var="nameHasError">
                            <form:errors path="name"/>
                        </c:set>
                        <c:set var="priceHasError">
                            <form:errors path="price"/>
                        </c:set>

                        <div class="form-group form-group-sm ${not empty codeHasError?"has-error":""}">
                            <label for="code" class="col-sm-2 control-label">
                                CODE</label>

                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" id="code" path="code"/>
                                <span class="help-block">${codeHasError}</span>
                            </div>
                        </div>

                        <div class="form-group form-group-sm ${not empty nameHasError?"has-error":""}">
                            <label for="name" class="col-sm-2 control-label">
                                NAME</label>

                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" id="name" path="name"/>
                                <span class="help-block">${nameHasError}</span>
                            </div>
                        </div>

                        <div class="form-group form-group-sm ${not empty priceHasError?"has-error":""}">
                            <label for="price" class="col-sm-2 control-label">
                                PRICE</label>

                            <div class="col-sm-10">
                                <form:input type="number"  step="0.01" class="form-control" id="price" path="price"/>
                                <span class="help-block">${priceHasError}</span>
                            </div>
                        </div>

                        <div class="form-group form-group-sm">
                            <label for="price" class="col-sm-2 control-label">
                                STATUS</label>

                            <div class="col-sm-10">
                                <form:select path="productStatus" items="${productStatusOptions}" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group form-group-sm">
                            <label for="descriptionShort"
                                   class="col-sm-2 control-label">
                                DESCRIPTION
                            </label>

                            <div class="col-sm-10">
                                <form:textarea class="form-control" rows="2" id="descriptionShort"
                                          path="descriptionShort"/>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-success btn-sm pull-right">
                            <i class="fa fa-plus"></i>
                            <span>Add product</span>
                        </button>

                    </form:form>

                </div>
            </div>
        </div>
    </div>
</div>

<script
        src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

</body>
</html>

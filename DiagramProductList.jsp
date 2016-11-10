<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<title>Product List</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
      integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<body>

<div class="container-fluid">
    <div class="row" style="margin-top: 30px">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Product list</h3>
                </div>
                <div class="panel-body">

                    <div class="row">
                        <div class="col-xs-12" style="margin-top: 10px;margin-bottom: 10px">
                            <a href="${pageContext.request.contextPath}/createProduct"
                               class="btn btn-success btn-sm pull-right"><i class="fa fa-plus"></i>New product</a>
                        </div>
                    </div>

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>In Stock</th>
                            <th>Options</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <th>${product.code}</th>
                                <td>${product.name}</td>
                                <td>${product.descriptionShort}</td>
                                <td>${product.price}$</td>
                                <td>

                                    <c:if test="${product.productStatus.equals('INSTOCK')}">
                                        <span class="text-success" title="${product.productStatus}">
                                        <i class="fa fa-check" aria-hidden="true"></i>
                                        </span>
                                    </c:if>
                                    <c:if test="${product.productStatus.equals('OUTOFSTOCK')}">
                                        <span class="text-danger" title="${product.productStatus}">
                                        <i class="fa fa-times" aria-hidden="true"></i>
                                        </span>
                                    </c:if>
                                </td>

                                <td>
                                    <a href="${pageContext.request.contextPath}/product/${product.code}"
                                       class="btn btn-default btn-xs">
                                        <i class="fa fa-info" aria-hidden="true"></i> Info
                                    </a>
                                    <a href="${pageContext.request.contextPath}/editProduct/${product.code}"
                                       class="btn btn-default btn-xs">
                                        <i class="fa fa-pencil" aria-hidden="true"></i> Edit
                                    </a>
                                    <a href="${pageContext.request.contextPath}/deleteProduct/${product.code}"
                                       class="btn btn-default btn-xs">
                                        <i class="fa fa-trash" aria-hidden="true"></i> Dell
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="row">


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

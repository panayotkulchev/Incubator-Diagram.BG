<%--
  Created by IntelliJ IDEA.
  User: pepo
  Date: 03.11.16
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <title>Product ${product.name}</title>
</head>
<body>

<div class="container-fluid">
    <div class="content-wrapper">
        <div class="">
            <div class="container">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Product details</h3>
                        </div>
                        <div class="panel-body">

                            <div class="product col-md-3 service-image-left">
                                <center>
                                    <img id="item-display" src="http://www.veromoda.com/dw/image/v2/ABBT_PRD/on/demandware.static/-/Sites-pim-catalog/default/dwc4333f35/pim-static/large/10173652_BlackBeauty_003_ProductLarge.jpg?sw=685" alt="">
                                </center>
                            </div>

                            <div class="container service1-items col-sm-2 col-md-2 pull-left">
                                <center>
                                    <a id="item-1" class="service1-item">
                                        <img src="http://www.veromoda.com/dw/image/v2/ABBT_PRD/on/demandware.static/-/Sites-pim-catalog/default/dwc4333f35/pim-static/large/10173652_BlackBeauty_003_ProductLarge.jpg?sw=685" alt="">
                                    </a>
                                    <a id="item-2" class="service1-item">
                                        <img src="http://www.veromoda.com/dw/image/v2/ABBT_PRD/on/demandware.static/-/Sites-pim-catalog/default/dw16759b27/pim-static/large/10173652_BlackBeauty_004_ProductLarge.jpg?sw=685" alt="">
                                    </a>
                                    <a id="item-3" class="service1-item">
                                        <img src="http://www.veromoda.com/dw/image/v2/ABBT_PRD/on/demandware.static/-/Sites-pim-catalog/default/dw4fe3a20b/pim-static/large/10173652_BlackBeauty_001_ProductLarge.jpg?sw=685" alt="">
                                    </a>
                                </center>
                            </div>
                            <div class="col-md-7">
                                <div class="product-title">${product.name}</div>
                                <div class="product-desc">${product.descriptionShort}</div>
                                <div class="product-rating"><i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star-o"></i> </div>
                                <hr>
                                <div class="product-price">$ ${product.price}</div>
                                <c:if test="${product.productStatus.equals('INSTOCK')}">
                                    <div class="product-stock">${product.productStatus}</div>
                                </c:if>
                                <c:if test="${product.productStatus.equals('OUTOFSTOCK')}">
                                    <div class="product-out-stock">${product.productStatus}</div>
                                </c:if>

                                <hr>
                                <div class="btn-group cart">
                                    <button type="button" class="btn btn-success">
                                        Add to cart
                                    </button>
                                </div>
                                <div class="btn-group wishlist">
                                    <button type="button" class="btn btn-danger">
                                        Add to wishlist
                                    </button>
                                </div>
                            </div>

                            <div class="container-fluid">
                                <div class="col-md-12 product-info">
                                    <ul id="myTab" class="nav nav-tabs nav_tabs">

                                        <li class="active"><a href="#service-one" data-toggle="tab">DESCRIPTION</a></li>
                                        <li><a href="#service-two" data-toggle="tab">PRODUCT INFO</a></li>
                                        <li><a href="#service-three" data-toggle="tab">REVIEWS</a></li>

                                    </ul>
                                    <div id="myTabContent" class="tab-content">
                                        <div class="tab-pane fade in active" id="service-one">

                                            <section class="container product-info">
                                                ${product.name} is the ideal price-performance solution for building or upgrading a Gaming PC. A single +12V rail provides up to 48A of reliable, continuous power for multi-core gaming PCs with multiple graphics cards. The ultra-quiet, dual ball-bearing fan automatically adjusts its speed according to temperature, so it will never intrude on your music and games. Blue LEDs bathe the transparent fan blades in a cool glow. Not feeling blue? You can turn off the lighting with the press of a button.

                                                <h3>${product.name} Features:</h3>
                                                <li>It supports the latest ATX12V v2.3 standard and is backward compatible with ATX12V 2.2 and ATX12V 2.01 systems</li>
                                                <li>An ultra-quiet 140mm double ball-bearing fan delivers great airflow at an very low noise level by varying fan speed in response to temperature</li>
                                                <li>80Plus certified to deliver 80% efficiency or higher at normal load conditions (20% to 100% load)</li>
                                                <li>0.99 Active Power Factor Correction provides clean and reliable power</li>
                                                <li>Universal AC input from 90~264V — no more hassle of flipping that tiny red switch to select the voltage input!</li>
                                                <li>Extra long fully-sleeved cables support full tower chassis</li>
                                                <li>A three year warranty and lifetime access to Corsair’s legendary technical support and customer service</li>
                                                <li>Over Current/Voltage/Power Protection, Under Voltage Protection and Short Circuit Protection provide complete component safety</li>
                                                <li>Dimensions: 150mm(W) x 86mm(H) x 160mm(L)</li>
                                                <li>MTBF: 100,000 hours</li>
                                                <li>Safety Approvals: UL, CUL, CE, CB, FCC Class B, TÜV, CCC, C-tick</li>
                                            </section>

                                        </div>
                                        <div class="tab-pane fade" id="service-two">

                                            <section class="container">

                                            </section>

                                        </div>
                                        <div class="tab-pane fade" id="service-three">

                                        </div>
                                    </div>
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>

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

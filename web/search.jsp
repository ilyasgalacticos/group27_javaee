<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.group27.db.Hotels" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.TimeZone" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <%@include file="head.jsp"%>
        <style>
            img{
                max-width: 100%;
            }
        </style>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container mt-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Search Hotels</li>
                </ol>
            </nav>
        </div>
        <div class="container mt-3">
            <form action="/search" method="get">
                <div class="row">
                    <div class="col-sm-3">
                        <label>NAME : </label>
                    </div>
                    <div class="col-sm-2">
                        <label>PRICE FROM : </label>
                    </div>
                    <div class="col-sm-2">
                        <label>PRICE TO : </label>
                    </div>
                    <div class="col-sm-2">
                        <label>STARS FROM : </label>
                    </div>
                    <div class="col-sm-2">
                        <label>STARS TO : </label>
                    </div>
                    <div class="col-sm-1">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <input type="text" class="form-control form-control-sm" name="key" value="<%=key%>">
                    </div>
                    <div class="col-sm-2">
                        <input type="number" class="form-control form-control-sm" name="price_from" value="<%=priceFrom%>">
                    </div>
                    <div class="col-sm-2">
                        <input type="number" class="form-control form-control-sm" name="price_to" value="<%=priceTo%>">
                    </div>
                    <div class="col-sm-2">
                        <input type="number" class="form-control form-control-sm" name="stars_from" value="<%=starsFrom%>">
                    </div>
                    <div class="col-sm-2">
                        <input type="number" class="form-control form-control-sm" name="stars_to" value="<%=starsTo%>">
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-primary btn-sm btn-block">FILTER</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="container mt-3">
            <div class="row">
                <div class="col-sm-12">
                    <%
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        format.setTimeZone(TimeZone.getTimeZone("UTC"));
                        ArrayList<Hotels> hotels = (ArrayList<Hotels>)request.getAttribute("hotels");
                        if(hotels!=null){
                            for(Hotels h : hotels){
                    %>
                        <div class="jumbotron">
                            <h2><%=h.getName()%></h2>
                            <h3>For <%=h.getPrice()%> USD</h3>
                            <h4><%=h.getStars()%> stars</h4>
                            <hr class="my-4">
                            <b>
                                Added by <%=h.getAuthor().getFullName()%> at <%=format.format(h.getAddedDate())%>
                            </b>
                            <hr class="my-4">
                            <a class="btn btn-primary btn-sm" href="/details?id=<%=h.getId()%>" role="button">Details</a>
                        </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>

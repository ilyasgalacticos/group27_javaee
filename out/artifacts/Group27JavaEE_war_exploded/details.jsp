<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.group27.db.Hotels" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="kz.bitlab.group27.db.Comments" %>
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
    <body class="mb-5 pb-5">
        <%@include file="navbar.jsp"%>
        <div class="container mt-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">All Hotels</li>
                </ol>
            </nav>
        </div>
        <div class="container mt-3">
            <div class="row">
                <div class="col-sm-12">
                    <%
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        format.setTimeZone(TimeZone.getTimeZone("UTC"));
                        Hotels hotel = (Hotels) request.getAttribute("hotel");
                        if(hotel!=null){
                    %>
                        <div class="jumbotron">
                            <h2><%=hotel.getName()%></h2>
                            <h3>For <%=hotel.getPrice()%> USD</h3>
                            <h4><%=hotel.getStars()%> stars</h4>
                            <hr class="my-4">
                            <b>
                                Added by <%=hotel.getAuthor().getFullName()%> at <%=format.format(hotel.getAddedDate())%>
                            </b>
                            <hr class="my-4">
                            <p>
                                <%=hotel.getDescription()%>
                            </p>
                        </div>
                        <%
                            if(currentUser!=null){
                        %>
                        <div class="row mt-5" id = "addCommentDiv">
                            <div class="col-sm-12">
                                <form action="/addcomment" method="post">
                                    <input type="hidden" name="hotel_id" value="<%=hotel.getId()%>">
                                    <h4>Add Comment</h4>
                                    <textarea class="form-control mt-3" rows="3" name="comment"></textarea>
                                    <button class="btn btn-success btn-sm mt-3">ADD COMMENT</button>
                                </form>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <h4 class="text-center mt-3">Comments</h4>
                        <div class="list-group mt-3">
                            <%
                                ArrayList<Comments> comments = (ArrayList<Comments>)request.getAttribute("comments");
                                if(comments!=null){
                                    for(Comments c : comments){
                            %>
                                <a href="JavaScript:void(0)" class="list-group-item list-group-item-action">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1"><%=c.getUser().getFullName()%></h5>
                                        <small class="text-muted"><%=format.format(c.getAddedDate())%></small>
                                    </div>
                                    <p class="mb-1">
                                        <%=c.getComment()%>
                                    </p>
                                </a>
                            <%
                                    }
                                }
                            %>
                        </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>

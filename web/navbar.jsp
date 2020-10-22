<%@ page import="kz.bitlab.group27.db.Users" %><%
    Users currentUser = (Users)session.getAttribute("USER");
%>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: rgba(54,102,120,0.08);">
        <a class="navbar-brand" href="/" style="font-weight: bold;"><img src="/logo/logo.png" style="width: 200px;"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <%
                    if(currentUser!=null){
                %>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile"><%=currentUser.getFullName()%> </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/addhotel">Add Hotel</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Logout </a>
                    </li>
                <%
                    }else{
                %>
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/register">Register </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Login </a>
                    </li>
                <%
                    }
                %>
            </ul>
            <%
                String key = "";
                if(request.getParameter("key")!=null){
                    key = request.getParameter("key");
                }

                String priceFrom = "";
                if(request.getParameter("price_from")!=null){
                    priceFrom = request.getParameter("price_from");
                }

                String priceTo = "";
                if(request.getParameter("price_to")!=null){
                    priceTo = request.getParameter("price_to");
                }

                String starsFrom = "";
                if(request.getParameter("stars_from")!=null){
                    starsFrom = request.getParameter("stars_from");
                }

                String starsTo = "";
                if(request.getParameter("stars_to")!=null){
                    starsTo = request.getParameter("stars_to");
                }
            %>
            <form class="form-inline my-2 my-lg-0" action="/search" method="get">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="key" value="<%=key%>">
                <button class="btn btn-outline-dark my-2 my-sm-0">Search</button>
            </form>
        </div>
    </nav>
</div>
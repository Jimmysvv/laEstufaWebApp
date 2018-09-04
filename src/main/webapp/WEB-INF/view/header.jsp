<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!Doctype HTML>
<html lang="en">
<head>
    <title>${title}</title>
    <meta charset="UTF-8">
    <meta name="description" content="Still under development.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <template:styles />
    <template:javaScripts/>
</head>
<body>
<c:set var="username" value="${(empty user.name) ? 'User' : user.name}" />
<div class="header">

    <nav class="uk-navbar">
        <div class="uk-navbar-right">
            <a class="uk-navbar-toggle" href="/" uk-icon="home" id="navmenu"></a>
            <a class="uk-navbar-toggle" href="/createpost" uk-icon="plus" id="navmenu"></a>
            <a class="uk-navbar-toggle" href="/discover" uk-icon="world" id="navmenu"></a>
            <a class="uk-navbar-toggle" uk-navbar-toggle-icon href="#offcanvas-slide" id="navmenu" uk-toggle></a>
        </div>
    </nav>

    <div id="offcanvas-slide" uk-offcanvas>
        <div class="uk-offcanvas-bar">
            <div class="uk-width-1-1@s uk-width-1-1@m">
                <ul class="uk-nav-default uk-nav-parent-icon" uk-nav="multiple: false">
                    <li class="uk-active"><a class="menu-bottom-line"><span uk-icon="menu"></span><span class="uk-margin-small-left">Menu</span></a></li>
                    <li class="uk-parent uk-active">
                        <a><span uk-icon="users"></span><span class="uk-margin-small-left">${username}</span></a>
                        <ul class="uk-nav-sub">
                            <li>
                                <a href="/user/${user.login}">
                                    <span uk-icon="user"></span><span class="uk-margin-small-left">Profile</span>
                                </a>
                            </li>
                            <li>
                                <a href="/search">
                                    <span uk-icon="search"></span><span class="uk-margin-small-left">Search</span>
                                </a>
                            </li>
                            <li>
                                <a href="/edit">
                                    <span uk-icon="settings"></span><span class="uk-margin-small-left">Preferences</span>
                                </a>
                            </li>
                            <li>
                                <a href="/logout">
                                    <span uk-icon="sign-out"></span><span class="uk-margin-small-left">Logout</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="uk-active"><a><span uk-icon="cog"></span><span class="uk-margin-small-left">Settings</span></a></li>
                    <li class="uk-active"><a><span uk-icon="info"></span><span class="uk-margin-small-left">About</span></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

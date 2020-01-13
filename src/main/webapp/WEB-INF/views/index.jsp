<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- saved from url=(0043)file:///C:/Users/OLESIA/Downloads/BEER.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Beer Market</title>
    <style>
        body {
            background: rgb(47, 71, 70); /* Цвет фона */
            color: #fc0; /* Цвет текста */
        }
        h1 {
            font-family: 'Times New Roman', Times, serif;
            font-size: 250%; /* Размер шрифта в процентах */
            color:rgb(173, 117, 196);
            text-align:center;
        }
        p, li {
            font-family: Verdana, Arial, Helvetica, sans-serif;
            font-size: 100%;
        }
        h2, h3 {
            font-family: Verdana, Arial, Helvetica, sans-serif;
            font-size: 120%;
            text-align:center;
            color:rgb(216, 79, 25);
        }

        footer {
            font-family: Verdana, Arial, Helvetica, sans-serif;
            font-size: 100%;
            text-align:left;
            color:rgb(45, 209, 238);
        }
    </style>

</head>
<body>

<h1> Beer Market </h1>
<h2> Виды и сорта пива </h2>

<p>     По способу изготовления, а именно, по типу сбраживания, пиво подразделяется на два основных вида - Эль и Lager.
    К разновидностям Эля относятся такие сорта, как: ячменное (Barley Wine), пшеничное (Weizen Weisse), портер (Porter), стаут (Stout), белое (Weisse), горькое (Bitter) и ламбик (Lambic).
    Lager - это в основной своей массе светлые сорта пива, но встречается и темный лагер. Благодаря небольшому количеству добавляемого хмеля, это пиво имеет легкий, мягкий вкус. Lager включает в себя такие основные сорта, как: пилзнер (Pilsner), мартовское пиво (Maerzen), бок (Bock), сухое (Dry), подкопченное пиво (Rauch), ледяное (Ice Beer) и бочковое (Draught). </p>

<h3> У нас вы можете купить </h3>
<ul>
    <li> Эль </li>
    <li>  Lager </li>
</ul>
<p>
</p>
<div class="container"></div>
    <form action="${pageContext.request.contextPath}/servlet/registration">
        <button>REGISTRATION NEW USER</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/purchase">
        <button>START PURCHASING</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/showBucket">
        <button>SHOW BUCKET</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/showOrder">
        <button>SHOW ORDER</button>
    </form>
    <hr>
    <h2>DO NOT TOUCH!</h2>
    <hr>
    <form action="${pageContext.request.contextPath}/servlet/getAllUsers">
        <button>SHOW ALL USERS</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/itemAdding">
        <button>ADD NEW ITEM</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/getAllItems">
        <button>SHOW ALL ITEMS</button>
    </form>

</body>
</html>
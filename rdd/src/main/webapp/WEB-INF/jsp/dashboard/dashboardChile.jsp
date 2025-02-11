<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>RDD</title>
<link rel="stylesheet" href="/rdd/css/generales.css">
<link rel="stylesheet" href="/rdd/css/style.css">
<link rel="stylesheet" href="/rdd/css/conciliacion.css">

<script src="/rdd/js/jquery/jquery-1.11.0.min.js"></script>
<script src="/rdd/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
<script type="text/javascript" src="/rdd/js/shadowbox/shadowbox.js"></script>
<script src="/rdd/js/Highcharts-3.0.10/js/highcharts.js"></script>

<style>
.tooltip {
	position: relative;
	display: inline-block;
	border-bottom: 1px dotted #ccc;
	color: #006080;
}

.tooltip .tooltiptext {
	visibility: hidden;
	position: absolute;
	width: 250px;
	background-color: #555;
	color: #fff;
	text-align: left;
	padding: 5px 0;
	border-radius: 6px;
	z-index: 1;
	transition: opacity 0.3s;
}

.tooltip:hover .tooltiptext {
	visibility: visible;
	opacity: 1;
}

.tooltip-right {
	top: -5px;
	left: 125%;
}

.tooltip-right::after {
	content: "";
	position: absolute;
	top: 10%;
	right: 100%;
	margin-top: -5px;
	border-width: 5px;
	border-style: solid;
	border-color: transparent #555 transparent transparent;
}

.custom_color_1 {
	background-color: #7DC6A3;
	opacity: .8;
}

.button {
	padding: 12px 24px;
	border-radius: 4px;
	border: 0;
	font-size: 15px;
	font-family: 'Barlow Reg';
	color: white;
	cursor: pointer;
	background-image: none !important;
	width: 100%;
	font-weight: 600;
}

.input {
	padding: 12px;
	border-radius: 4px;
	width: 100%;
	margin-bottom: 1rem;
	margin-top: .5rem;
	padding-left: 10px;
	border: 1px solid #D0D0D0;
	border-radius: 4px;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
	background-color: #fff;
	font-family: 'Barlow Reg';
	box-sizing: border-box;
	-webkit-appearance: none;
	font-size: 15px;
	transition: all 0.1s;
}

.label {
	font-size: 15px;
	color: #888;
	margin: 0 0 8px 0;
	line-height: inherit;
	font-family: 'Barlow Reg';
	font-weight: 400;
}

#contenidos {
	width: 74%;
	overflow: hidden;
	z-index: 98;
	position: relative;
	background-color: #fff;
	border-radius: 10px;
	padding: 40px 50px 50px 50px;
	float: right;
	height: 965px;
}

#asideDash {
	position: absolute;
	float: left;
	width: 20%;
	z-index: 100;
	clear: both;
}

#sucursalesDash {
	border-radius: 10px;
	position: relative;
	width: 100%;
	height: 1050px;
	margin-top: 5px;
	overflow: auto;
	background-color: #fff;
	padding: 6px;
}

#contenidoDash {
	border-radius: 10px;
	position: relative;
	float: left;
	width: 610px;
	height: 600px;
	margin-top: 40px;
	background-color: #fff;
}

#cuadro-flotanteDash {
	position: absolute;
	top: 700px;
	height: 300px;
	width: 100%;
	margin-left: 90%;
	text-align: center;
	border-radius: 10px;
	z-index: 101;
}

.columna {
	width: 50%;
	float: left;
}

.columnasu {
	width: 100%;
	float: left;
	columns: 2;
}

@media ( max-width : 500px) {
	.columna {
		width: auto;
		float: none;
	}
}

body {
	background-color: #F3F1EE;
	text-align: center;
	font-family: calibri;
	overflow: hidden;
	position: relative;
	width: 1700px;
	margin: 0 auto;
	font-weight: normal;
	font-style: normal;
	background-image: url(../images/home_bg.png);
	background-size: 6px;
	background-repeat: repeat-x;
	overflow: hidden;
}

.oficinasDash img {
	width: 145px;
}

.articles {
	width: 1253px;
	/* height: 50px; */
	position: relative;
	margin-top: 50px;
}

.oficinasDash ul li span {
	position: absolute;
	top: 7px;
	left: 26px;
	font-family: 'Barlow Semi';
	font-size: 12px;
}

.oficinasDash ul li {
	position: relative;
	text-align: left;
	list-style: none;
}

#caja_fuerteDash {
	position: relative;
	width: 163px;
	padding: 0;
	margin-top: 0px;
}

#segCajaDash {
	position: absolute;
	bottom: 80px;
	width: 130px;
	margin-left: 20px;
	height: 110px;
	padding: 0;
	list-style: none;
	text-align: right;
}

#segCajaDash li {
	margin-top: 8px;
	font-family: 'Barlow Semi';
	font-size: 16px;
	font-weight: 600;
	text-align: center;
}

#segCajaDash .porcentagebig {
	font-size: 40px;
	font-family: 'Barlow Semi';
	font-weight: 600;
	text-align: center;
}

#segCajaDash .porcentage {
	background: #D8D8D8;
	width: 100%;
	height: 12px;
	display: block;
	border-radius: 5px;
}

#segCajaDash .porcentage span {
	background: #7DC6A3;
	height: 100%;
	display: block;
	border-radius: 5px;
}

#caja_fuerteDash img {
	width: 130px;
}

#rellenoDash {
	height: 30%;
	width: 130px;
	background: #7DC6A3;
	opacity: 1;
	position: absolute;
	border-radius: 4px;
	left: 17px;
	bottom: 12px;
	z-index: 102;
	mix-blend-mode: multiply;
}
</style>

</head>
<body>

	<div>

		<div style="background: black;">
			<a href="#" id="botonAt" style="position: relative;"><img
				src="resources/images/botonAtras.png" style="width: 24px;"></a> <a
				href="#" id="botonImg" style="position: relative;"><img
				src="resources/images/botonPausa.png" style="width: 24px;"></a> <a
				href="#" id="botonSig" style="position: relative;"><img
				src="resources/images/botonSiguiente.png" style="height: 24px;"></a>
			<a class="botonHome" href="home.htm" id="home" style="color: white">IR
				AL ADMINISTRADOR</a>
		</div>


		<div id="contenedor">
		
			<div id="asideDash">
				<div id="sucursalesDash">
					<h1>
						<fmt:message key="HEADER.INTERNAS" />
						:
					</h1>
					<div class="columnasu">
						<div class="oficinasDash internas">

							<ul>
								<c:forEach items="${oficinasInt}" var="b">
									<li><img
										src="resources/images/oficinas_internas_<c:out value=" ${b.dif}"/>.png"
										/> <span><c:out value="${b.oficina}" /></span></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<h1>
						<fmt:message key="HEADER.EXTERNAS" />
						:
					</h1>
					<div class="columnasu">
						<div class="oficinasDash externas">

							<ul>
								<c:forEach items="${oficinasExt}" var="b">
									<li><img
										src="resources/images/oficinas_externas_<c:out value=" ${b.dif}"/>.png"
										/> <span><c:out value="${b.oficina}" /></span></li>
								</c:forEach>
							</ul>
						</div>
					</div>

				</div>

			</div>


			<div id="wrapper">

				<div id="cuadro-flotanteDash">

					<div id="loadingFlotante" class="loading">
						<img src="resources/images/ajax-loader.gif" />
					</div>


					<div id="caja_fuerteDash">
						<div id="rellenoDash"></div>
						<img src="resources/images/caja-01.png" />
					</div>
					<ul id="segCajaDash">
					</ul>
				</div>

				<div id="contenidos">

					<div class="ruta">
						<ul class="ruta-nav">
							<li><a href="#"></a><input type="hidden" class="id"
								value="1" /></li>
							<li><a href="#"></a><input type="hidden" class="id"
								value="2" /></li>
							<li><a href="#"></a><input type="hidden" class="id"
								value="3" /></li>
							<li><a href="#"></a><input type="hidden" class="id"
								value="4" /></li>
							<li><a href="#"></a><input type="hidden" class="id"
								value="5" /></li>
							<li><a href="#"></a><input type="hidden" class="id"
								value="6" /></li>
							<li><a href="#"></a><input type="hidden" class="id"
								value="7" /></li>
						</ul>
					</div>
					<div id="contenidoDash">

						<jsp:include page="loading.jsp" />
						<div class="columna">
							<p class="titulo1">
								<fmt:message key="HOME.TRANSACCIONES" />
								 <span
									style="position: relative; margin-left: 10px; font-size: 14px; width: 150px; display: inline-block;"></span>
							</p>
							<div class="articles">
								<span class="esquina"></span>

								<div id="container" class="container"></div>
							</div>
						</div>
					</div>

					<div id="contenidoDash">
						<div class="columna">
							<p class="titulo1">
								<fmt:message key="HOME.MONTOS" />
								<%= ft.format(new Date()) %> <span
									style="position: relative; margin-left: 10px; font-size: 14px; width: 150px; display: inline-block;"></span>
							</p>
							<div class="articles">

								<span class="esquina"></span>

								<div id="container2" class="container"></div>
							</div>
						</div>

					</div>

				</div>


			</div>
		</div>
</body>
</html>
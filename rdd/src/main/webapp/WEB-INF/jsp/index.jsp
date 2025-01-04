<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>RDD</title>
<link rel="stylesheet" href="/rdd/css/generales.css">
<link rel="stylesheet" href="/rdd/css/style.css">
</head>
<body>

	<div class="container py 5">
		<div id="left">
			<div id="wrap">
				<div id="main">
					<div id="wrapper">
						<div class="logo_wrapper">
							<div class="logo_a">
								<img id="logo" class="form_logo_a"
									th:src="@{/images/logo_a.png}" border="0" name="logo">
							</div>
							<div class="logo_b">
								<img id="logo" class="form_logo_b"
									th:src="@{/images/logo_b.png}" border="0" name="logo">
							</div>
						</div>
						<div id="content" style="display: block;">
							<a class="btn btn-success" href="/rdd/oauth2/authorization/azure"><button
									type="button" style="margin-top: 10px"
									class="button custom_color_1">Login</button></a> <span
								style="color: red;"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer id="footer" style="text-align: center;">
		Copyright © 2014 <span style="margin-right: 10px;"></span> www.ndt.cl
		<span style="margin-right: 10px;"></span> Diseñado por NDT Consulting
		&amp; Development
	</footer>
</body>
</html>
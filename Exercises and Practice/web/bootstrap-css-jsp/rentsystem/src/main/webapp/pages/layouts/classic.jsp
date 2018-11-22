<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title><tiles:getAsString name="title" />
</title>
<tiles:insertAttribute name="cssFile"></tiles:insertAttribute>
</head>
<body>
	<div id="wrapper">
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>

		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>

		<div id="page">
			<div id="page-bgtop">
				<div id="page-bgbtm">
					<div id="content">
						<tiles:insertAttribute name="body" />

						<div style="clear: both;">&nbsp;</div>
					</div>

					<div id="sidebar">
						<tiles:insertAttribute name="sideBar" />
					</div>

					<div style="clear: both;">&nbsp;</div>
				</div>
			</div>
		</div>

	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
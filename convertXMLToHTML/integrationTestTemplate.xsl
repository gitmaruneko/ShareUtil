<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
 
	<xsl:template match="/">
		<html>
 
			<head>
				<style type="text/css">
					table.tfmt {
					border: 1px ;
					}
 
					td.colfmt {
					border: 1px ;
					background-color: white;
					color: black;
					text-align:right;
					}
 
					th {
					background-color: #2E9AFE;
					color: white;
					}
 
				</style>
			</head>
 
			<body>
				<table class="tfmt">
					<tr>
						<th style="width:250px">Result</th>
						<th style="width:600px">Test Case</th>
 
 
					</tr>
 
					<xsl:for-each select="ITEGRATIONTEST/ITEM">
 
						<tr>
							<td class="colfmt">
								<xsl:value-of select="RESULT" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="TESTCASE" />
							</td>
						</tr>
 
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/testsuite">
        <html lang="en">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <meta charset="utf-8"/>
            <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
            <meta name="description" content=""/>
            <meta name="author" content=""/>

            <title>Theme Template for Bootstrap</title>

            <!-- Bootstrap core CSS -->
            <link href="./css/bootstrap.min.css" rel="stylesheet"/>
            <!-- Bootstrap theme -->
            <link href="./css/bootstrap-theme.min.css" rel="stylesheet"/>
            <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
            <link href="./ie10-viewport-bug-workaround.css" rel="stylesheet"/>

            <!-- Custom styles for this template -->
            <link href="./css/theme.css" rel="stylesheet"/>

            <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
            <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        </head>
          <body>
            <div class="container theme-showcase" role="main">
                <h1>Mutation Result</h1>
              <div class="row">
                <div class="col-md-6">
                  <table class="table table-striped table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>TestClass</th>
                        <th>Class</th>
                        <th>Error</th>
                      </tr>
                    </thead>
                    <tbody>
                      <xsl:for-each select="testcase">
                          <tr>
                              <td><xsl:value-of select="@name"/></td>
                              <td><xsl:value-of select="@classname"/></td>
                              <td><xsl:value-of select="failure/@type"/></td>
                          </tr>
                      </xsl:for-each>
                    </tbody>
                  </table>
                </div>
              </div>

            </div> <!-- /container -->
                <h2>Mutation Result</h2>
                <table border="1">
                    <tr bgcolor="#bbbbbb">
                        <th>TestClass</th>
                        <th>Class</th>
                        <th>Error</th>
                    </tr>
                    <xsl:for-each select="testcase">
                        <tr>
                            <td><xsl:value-of select="@name"/></td>
                            <td><xsl:value-of select="@classname"/></td>
                            <td><xsl:value-of select="failure/@type"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
                <script src="./css/jquery.min.js"></script>
                <script src="./css/bootstrap.min.js"></script>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
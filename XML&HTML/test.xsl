<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/testsuite">
        <html>
            <body>
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
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
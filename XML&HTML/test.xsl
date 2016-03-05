<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/testsuite">
        <html>
            <body>
                <xsl:for-each select="Mutation">
                <h2><xsl:value-of select="@operateur"/><xsl:value-of select="@selecteur"/></h2>
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
                            <td><xsl:value-of select="failure/@type"/><xsl:value-of select="@compilation"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
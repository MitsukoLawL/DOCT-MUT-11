<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/root">
        <html>
            <body>

                <xsl:variable name="TotalV">
                    <xsl:value-of select="0"/>
                </xsl:variable>

                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                    Total Mutants tués : <span id="mutantT"><xsl:value-of select="count(//Mutation[@failures!='0'])"/></span>
                        <br/> Total Mutants vivants : <span id="mutantV"><xsl:value-of select="count(//Mutation) - count(//Mutation[@failures!='0']) - count(//compilation_error) "/></span>
                        <br/> Total Mutants mort nés : <span id="mutantMN"><xsl:value-of select="count(//compilation_error)"/></span>

                </div>
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
                            <td><xsl:value-of select="failure/@type"/><xsl:value-of select="compilation_error"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
                    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                    Tests totaux : <span class="totTest"><xsl:value-of select="count(testcase)"/></span>
                    <br/> Test success : <span class="successTest"><xsl:value-of select="count(testcase) - (count(testcase/failure) + count(testcase/compilation_error))"/></span>
                    <br/> Error compilation : <span ><xsl:value-of select="count(testcase/compilation_error)"/></span>
                </div>

                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
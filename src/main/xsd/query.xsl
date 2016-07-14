<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Filter Query</h2>
                
                
                
                <xsl:value-of select="query/polynomial/circle/centre/@longitude"/>
                <xsl:value-of select="query/polynomial/circle"/>
                
                


                <xsl:for-each select="query/filter/item">

                    <xsl:value-of select="key"/>
                    <xsl:choose>

                        <xsl:when test="operator = 'equals'">
                            <xsl:value-of select="'='"/>
                        </xsl:when>

                        <xsl:when test="operator = 'not equals'">
                            <xsl:value-of select="'!='"/>
                        </xsl:when>

                        <xsl:otherwise>
                            <xsl:value-of select="'='"/>
                        </xsl:otherwise>

                    </xsl:choose>

                    <xsl:value-of select="value"/>


                </xsl:for-each>



            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

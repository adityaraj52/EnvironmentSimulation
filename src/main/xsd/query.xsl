<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="query/polynomial/list/position">
        <h1><xsl:apply-templates/></h1>
    </xsl:template>
    
    <xsl:template match="/">
        <html>
            <body>                
                <xsl:if test="query/polynomial/circle">
                    <xsl:value-of select="query/polynomial/circle/centre/@latitude"/>
                       
                    <xsl:value-of select="query/polynomial/circle/centre/@longitude"/>
                       
                    <xsl:value-of select="query/polynomial/circle/radius"/>
                       
               </xsl:if>        
                
                <xsl:if test="query/polynomial/rectangle">
                    <xsl:value-of select="query/polynomial/rectangle/lefttop/@latitude"/>
                       
                    <xsl:value-of select="query/polynomial/rectangle/lefttop/@longitude"/>
                       
                    <xsl:value-of select="query/polynomial/rectangle/bottomright/@latitude"/>
                       
                    <xsl:value-of select="query/polynomial/rectangle/bottomright/@longitude"/>
                       
               </xsl:if>
 
                <xsl:if test="query/polynomial/list">
                    <xsl:value-of select="query/polynomial/list/position/@latitude"/>
                     
                    <xsl:value-of select="query/polynomial/list/position/@longitude"/>
                     
                </xsl:if>
                

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
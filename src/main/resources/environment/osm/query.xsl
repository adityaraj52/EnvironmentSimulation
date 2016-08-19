<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" xmlns:xs = "http://www.w3.org/2001/XMLSchema"
                version = "1.0" exclude-result-prefixes = "xs">

    <!-- transform to Overpass-Query-Language http://wiki.openstreetmap.org/wiki/Overpass_API -->


    <!-- General templates for the follwoing name related symbols -->
    <xsl:template name="comma">
        <xsl:text>,</xsl:text>
    </xsl:template>

    <xsl:template name="whitespace">
        <xsl:text> </xsl:text>
    </xsl:template>

    <xsl:template name="open_big_bracket">
        <xsl:text>[</xsl:text>
    </xsl:template>

    <xsl:template name="close_big_bracket">
        <xsl:text>]</xsl:text>
    </xsl:template>

    <xsl:template name="open_small_bracket">
        <xsl:text>(</xsl:text>
    </xsl:template>

    <xsl:template name="close_small_bracket">
        <xsl:text>)</xsl:text>
    </xsl:template>

    <xsl:template name="semicolon">
        <xsl:text>;</xsl:text>
    </xsl:template>


    <!-- variable declarations goes here -->
    <xsl:variable name="list_storage">
        <xsl:apply-templates select="query/polynomial/list/position"/>
    </xsl:variable>

    <xsl:variable name="whitespace">
        <xsl:call-template name="whitespace"/>
    </xsl:variable>


    <!-- main Template goes here -->
    <xsl:template match="/">

        <xsl:text>node</xsl:text>

        <xsl:call-template name="open_small_bracket"/>

        <xsl:apply-templates select="/query/polynomial"/>

        <xsl:call-template name="close_small_bracket"/>

        <xsl:apply-templates select="/query/filter/item"/>
        <xsl:call-template name="semicolon"/>
    </xsl:template>


    <!-- template for querying polynomial -->
    <xsl:template match="/query/polynomial">

        <!-- choose one of the following choices available among list/rectangle/circle -->
        <xsl:choose>

            <xsl:when test="/query/polynomial/list">

                <xsl:text>poly:"</xsl:text>

                <!-- trimming the string from right end to remove the extra white space to match overpass ql -->
                <xsl:call-template name="string-rtrim">
                    <xsl:with-param name="string" select="$list_storage"/>
                </xsl:call-template>
                <xsl:text>"</xsl:text>

            </xsl:when>

            <xsl:when test="/query/polynomial/rectangle">
                <xsl:apply-templates select="rectangle"/>
            </xsl:when>

            <xsl:otherwise>
                <xsl:apply-templates select="circle"/>
            </xsl:otherwise>

        </xsl:choose>
    </xsl:template>


    <xsl:template match="list/position">

        <xsl:call-template name="read_coordinate_with_spaces"/>
        <xsl:call-template name="whitespace"/>

    </xsl:template>


    <xsl:template match="rectangle">
        <xsl:apply-templates select="bottomright"/>
        <xsl:call-template name="comma"/>
        <xsl:apply-templates select="topleft"/>
    </xsl:template>


    <xsl:template match="topleft">
        <xsl:call-template name="read_coordinate"/>
    </xsl:template>


    <xsl:template match="bottomright">
        <xsl:call-template name="read_coordinate"/>
    </xsl:template>


    <xsl:template match="circle">
        <xsl:apply-templates select="centre"/>
        <xsl:call-template name="comma"/>
        <xsl:apply-templates select="radius"/>
    </xsl:template>


    <xsl:template match="centre">
        <xsl:call-template name="read_coordinate"/>
    </xsl:template>


    <xsl:template match="radius">
        <xsl:value-of select="."/>
    </xsl:template>


    <xsl:template name="read_coordinate">
        <xsl:apply-templates select="@latitude"/>
        <xsl:call-template name="comma"/>
        <xsl:apply-templates select="@longitude"/>
    </xsl:template>


    <xsl:template name="read_coordinate_with_spaces">
        <xsl:apply-templates select="@latitude"/>
        <xsl:call-template name="whitespace"/>
        <xsl:apply-templates select="@longitude"/>
    </xsl:template>


    <xsl:template match="@latitude">
        <xsl:value-of select="."/>
    </xsl:template>


    <xsl:template match="@longitude">
        <xsl:value-of select="."/>
    </xsl:template>


    <xsl:template name="string-rtrim">
        <xsl:param name="string"/>
        <xsl:param name="trim" select="$whitespace"/>

        <xsl:variable name="length" select="string-length($string)"/>

        <xsl:if test = "$length > 0">
            <xsl:choose>
                <xsl:when test="contains($trim, substring($string, $length, 1))">
                    <xsl:call-template name="string-rtrim">
                        <xsl:with-param name="string" select="substring($string, 1, $length - 1)"/>
                        <xsl:with-param name="trim" select="$trim"/>
                    </xsl:call-template>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$string"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
    </xsl:template>


    <xsl:template match="/query/filter/item">
        <xsl:apply-templates select="key"/>
        <xsl:apply-templates select="operator"/>
        <xsl:apply-templates select="value"/>
    </xsl:template>


    <xsl:template match="key">
        <xsl:call-template name="open_big_bracket"/>
        <xsl:value-of select="."/>
    </xsl:template>


    <xsl:template match="operator">

        <xsl:choose>

            <xsl:when test=". = 'equals'">
                <xsl:value-of select="'='"/>
            </xsl:when>

            <xsl:when test=". = 'not equals'">
                <xsl:value-of select="'!='"/>
            </xsl:when>

            <xsl:otherwise>
                <xsl:value-of select="'='"/>
            </xsl:otherwise>

        </xsl:choose>

    </xsl:template>

    <xsl:template match="value">
        <xsl:value-of select="."/>
        <xsl:call-template name="close_big_bracket"/>
    </xsl:template>

</xsl:stylesheet>

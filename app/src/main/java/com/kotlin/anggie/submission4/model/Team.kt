package com.kotlin.anggie.submission4.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        val idTeam: String? = null,

        @SerializedName("idSoccerXML")
        val idSoccerXML: String? = null,

        @SerializedName("intLoved")
        val intLoved: String? = null,

        @SerializedName("strTeam")
        val strTeam: String? = null,

        @SerializedName("strTeamShort")
        val strTeamShort: Any?,

        @SerializedName("strAlternate")
        val strAlternate: String? = null,

        @SerializedName("intFormedYear")
        val intFormedYear: String? = null,

        @SerializedName("strSport")
        val strSport: String? = null,

        @SerializedName("strLeague")
        val strLeague: String? = null,

        @SerializedName("idLeague")
        val idLeague: String? = null,

        @SerializedName("strDivision")
        val strDivision: Any?,

        @SerializedName("strManager")
        val strManager: String? = null,

        @SerializedName("strStadium")
        val strStadium: String? = null,

        @SerializedName("strKeywords")
        val strKeywords: String? = null,

        @SerializedName("strRSS")
        val strRSS: String? = null,

        @SerializedName("strStadiumThumb")
        val strStadiumThumb: String? = null,

        @SerializedName("strStadiumDescription")
        val strStadiumDescription: String? = null,

        @SerializedName("strStadiumLocation")
        val strStadiumLocation: String? = null,

        @SerializedName("intStadiumCapacity")
        val intStadiumCapacity: String? = null,

        @SerializedName("strWebsite")
        val strWebsite: String? = null,

        @SerializedName("strFacebook")
        val strFacebook: String? = null,

        @SerializedName("strTwitter")
        val strTwitter: String? = null,

        @SerializedName("strInstagram")
        val strInstagram: String? = null,

        @SerializedName("strDescriptionEN")
        val strDescriptionEN: String? = null,

        @SerializedName("strGender")
        val strGender: String? = null,

        @SerializedName("strCountry")
        val strCountry: String? = null,

        @SerializedName("strTeamBadge")
        val strTeamBadge: String? = null,

        @SerializedName("strTeamJersey")
        val strTeamJersey: String? = null,

        @SerializedName("strTeamLogo")
        val strTeamLogo: String? = null,

        @SerializedName("strTeamFanart1")
        val strTeamFanart1: String? = null,

        @SerializedName("strTeamFanart2")
        val strTeamFanart2: String? = null,
        
        @SerializedName("strTeamFanart3")
        val strTeamFanart3: String? = null,

        @SerializedName("strTeamFanart4")
        val strTeamFanart4: String? = null,

        @SerializedName("strTeamBanner")
        val strTeamBanner: String? = null,

        @SerializedName("strYoutube")
        val strYoutube: String? = null,

        @SerializedName("strLocked")
        val strLocked: String? = null
)
package neflo.dev.tripcount.ui.theme

import androidx.compose.ui.graphics.Color

// Tonal Palettes

// *********** WHEN TONE IS < 60 TEXT MUST BE WHITE
// *********** WHEN TONE IS >= 60 TEXT MUST BE BLACK

// ************ Primary
val P0 = Color(0xFF000000)
val P10 = Color(0xFF21005D)
val P20 = Color(0xFF381E72)
val P30 = Color(0xFF4F378B)
val P40 = Color(0xFF6750A4)
val P50 = Color(0xFF7F67BE)
val P60 = Color(0xFF9A82DB)
val P70 = Color(0xFFB69DF8)
val P80 = Color(0xFFD0BCFF)
val P90 = Color(0xFFEADDFF)
val P95 = Color(0xFFF6EDFF)
val P99 = Color(0xFFFFFBFE)
val P100 = Color(0xFFFFFFFF)

// ************ Secondary
val S0 = Color(0xFF000000)
val S10 = Color(0xFF1D192B)
val S20 = Color(0xFF332D41)
val S30 = Color(0xFF4A4458)
val S40 = Color(0xFF625B71)
val S50 = Color(0xFF7A7289)
val S60 = Color(0xFF958DA5)
val S70 = Color(0xFFB0A7C0)
val S80 = Color(0xFFCCC2DC)
val S90 = Color(0xFFE8DEF8)
val S95 = Color(0xFFF6EDFF)
val S99 = Color(0xFFFFFBFE)
val S100 = Color(0xFFFFFFFF)

// ************ Tertiary
val T0 = Color(0xFF000000)
val T10 = Color(0xFF31111D)
val T20 = Color(0xFF492532)
val T30 = Color(0xFF633B48)
val T40 = Color(0xFF7D5260)
val T50 = Color(0xFF986977)
val T60 = Color(0xFFB58392)
val T70 = Color(0xFFD29DAC)
val T80 = Color(0xFFEFB8C8)
val T90 = Color(0xFFFFD8E4)
val T95 = Color(0xFFFFECF1)
val T99 = Color(0xFFFFFBFA)
val T100 = Color(0xFFFFFFFF)

// ************ Error
val E0 = Color(0xFF000000)
val E10 = Color(0xFF410E0B)
val E20 = Color(0xFF601410)
val E30 = Color(0xFF8C1D18)
val E40 = Color(0xFFB3261E)
val E50 = Color(0xFFDC362E)
val E60 = Color(0xFFE46962)
val E70 = Color(0xFFEC928E)
val E80 = Color(0xFFF2B8B5)
val E90 = Color(0xFFF9DEDC)
val E95 = Color(0xFFFCEEEE)
val E99 = Color(0xFFFFFBF9)
val E100 = Color(0xFFFFFFFF)

// ************ Neutral
val N0 = Color(0xFF000000)
val N4 = Color(0xFF0F0D13)
val N6 = Color(0xFF141218)
val N10 = Color(0xFF1D1B20)
val N12 = Color(0xFF211F26)
val N17 = Color(0xFF2B2930)
val N20 = Color(0xFF322F35)
val N22 = Color(0xFF36343B)
val N24 = Color(0xFF3B383E)
val N30 = Color(0xFF48464C)
val N40 = Color(0xFF605D64)
val N50 = Color(0xFF79767D)
val N60 = Color(0xFF938F96)
val N70 = Color(0xFFAEA9B1)
val N80 = Color(0xFFCAC5CD)
val N87 = Color(0xFFDED8E1)
val N90 = Color(0xFFE6E0E9)
val N92 = Color(0xFFECE6F0)
val N94 = Color(0xFFF3EDF7)
val N95 = Color(0xFFF5EFF7)
val N96 = Color(0xFFF7F2FA)
val N98 = Color(0xFFFEF7FF)
val N99 = Color(0xFFFFFBFF)
val N100 = Color(0xFFFFFFFF)

// ************ Neutral Variant
val NV0 = Color(0xFF000000)
val NV10 = Color(0xFF1D1A22)
val NV20 = Color(0xFF322F37)
val NV30 = Color(0xFF49454F)
val NV40 = Color(0xFF605D66)
val NV50 = Color(0xFF79747E)
val NV60 = Color(0xFF938F99)
val NV70 = Color(0xFFAEA9B4)
val NV80 = Color(0xFFCAC4D0)
val NV90 = Color(0xFFE7E0EC)
val NV95 = Color(0xFFF5EEFA)
val NV99 = Color(0xFFFFFBFE)
val NV100 = Color(0xFFFFFFFF)

// ********************************* Light Theme
val PrimaryLight = P40
val OnPrimaryLight = P100
val PrimaryContainerLight = P90
val OnPrimaryContainerLight = P30

val SecondaryLight = S80
val OnSecondaryLight = S20
val SecondaryContainerLight = S30
val OnSecondaryContainerLight = S90

val TertiaryLight = T80
val OnTertiaryLight = T20
val TertiaryContainerLight = T30
val OnTertiaryContainerLight = T90

val ErrorLight = E80
val OnErrorLight = E20
val ErrorContainerLight = E30
val OnErrorContainerLight = E90

val SurfaceLight = N98
val SurfaceDimLight = N87
val SurfaceBrightLight = N99
val SurfaceContainerLowestLight = N100
val SurfaceContainerLowLight = N96
val SurfaceContainerLight = N94
val SurfaceContainerHighLight = N92
val SurfaceContainerHighestLight = N90
val OnSurfaceLight = N10
val OnSurfaceVariantLight = NV30
val OutlineLight = NV50
val OutlineVariantLight = NV80

val InverseSurfaceLight = N20
val InverseOnSurfaceLight = N95
val InversePrimaryLight = P80

// ********************************* Dark Theme
val PrimaryDark = P80
val OnPrimaryDark = P20
val PrimaryContainerDark = P30
val OnPrimaryContainerDark = P90

val SecondaryDark = S40
val OnSecondaryDark = S100
val SecondaryContainerDark = S90
val OnSecondaryContainerDark = S30

val TertiaryDark = T40
val OnTertiaryDark = T100
val TertiaryContainerDark = T90
val OnTertiaryContainerDark = T30

val ErrorDark = E40
val OnErrorDark = E100
val ErrorContainerDark = E90
val OnErrorContainerDark = E30

val SurfaceDark = N6
val SurfaceDimDark = N4
val SurfaceBrightDark = N24
val SurfaceContainerLowestDark = N4
val SurfaceContainerLowDark = N10
val SurfaceContainerDark = N12
val SurfaceContainerHighDark = N17
val SurfaceContainerHighestDark = N22
val OnSurfaceDark = N90
val OnSurfaceVariantDark = NV80
val OutlineDark = NV60
val OutlineVariantDark = NV30

val InverseSurfaceDark = N90
val InverseOnSurfaceDark = N20
val InversePrimaryDark = P40

// Fixed Theme

val Scrim = N0
val Shadow = N0

val PrimaryFixed = P90
val PrimaryFixedDim = P80
val OnPrimaryFixed = P10
val OnPrimaryFixedDim = P30

val SecondaryFixed = S90
val SecondaryFixedDim = S80
val OnSecondaryFixed = S10
val OnSecondaryFixedDim = S30

val TertiaryFixed = T90
val TertiaryFixedDim = T80
val OnTertiaryFixed = T10
val OnTertiaryFixedDim = T30
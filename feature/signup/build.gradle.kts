plugins {
    id("teampatch.android.library")
    id("teampatch.android.library.compose")
    id("teampatch.android.library.test")
    id("teampatch.android.hilt")
    id("teampatch.android.feature")
    id("kotlin-parcelize")
}

android {
    namespace = "com.agvber.feature.signup"
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
}
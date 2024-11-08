plugins {
    id("teampatch.android.library")
    id("teampatch.android.library.compose")
    id("teampatch.android.library.test")
    id("teampatch.android.hilt")
    id("teampatch.android.feature")
}

android {
    namespace = "com.agvber.feature.home"
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
}
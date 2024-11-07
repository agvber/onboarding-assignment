plugins {
    id("teampatch.android.library")
    id("teampatch.android.library.test")
    id("teampatch.android.hilt")
}

android {
    namespace = "com.agvber.core.data"
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:database"))
}
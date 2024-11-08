plugins {
    id("teampatch.android.library")
    id("teampatch.android.library.test")
    id("teampatch.android.hilt")
}

android {
    namespace = "com.agvber.core.database"
}

dependencies {

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing)
}
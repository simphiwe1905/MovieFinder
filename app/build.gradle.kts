plugins {
    // Android Application Plugin
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.moviefinder"
    compileSdk = 36 // Target SDK version

    defaultConfig {
        applicationId = "com.example.moviefinder"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            // Disable code shrinking/obfuscation for release builds
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Android Support Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Retrofit - API communication
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp Logging - for debugging network calls
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Kotlin Coroutines - for asynchronous tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // Lifecycle / ViewModel - for UI data management
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")

    // Glide - for loading and caching movie poster images
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Testing Libraries
    testImplementation(libs.junit)
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.7.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("org.json:json:20231013")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}
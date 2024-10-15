plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.smart_egbuchulem.workout_crunch"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.smart_egbuchulem.workout_crunch"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
//    implementation(libs.firebase.auth)
//    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform("com.google.firebase:firebase-bom:33.4.0"))
//
//    implementation("com.google.firebase:firebase-auth:21.0.8")
//    implementation("com.google.android.gms:play-services-auth:20.5.0")

    implementation("com.google.firebase:firebase-auth:21.0.1'")
// Google Sign-In
    implementation("com.google.android.gms:play-services-auth:19.2.0")
// Firebase Realtime Database (optional, if using Firebase database)
    implementation("com.google.firebase:firebase-database:20.0.5")
//    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("io.coil-kt:coil:2.4.0")
    // If you need SVG support, you can add:
    implementation("io.coil-kt:coil-svg:2.4.0")


}
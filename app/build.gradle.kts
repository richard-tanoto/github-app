plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.richard.githubapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.richard.githubapp"
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appCompat)
    implementation(libs.google.material)
    implementation(libs.androidx.constraintLayout)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.espresso)

    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
}
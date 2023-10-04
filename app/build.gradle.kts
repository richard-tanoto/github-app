plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
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
        buildConfigField("String", "API_KEY", "\"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\"")
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
        buildConfig = true
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

    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)

    implementation(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)

    implementation(libs.glide.core)
    ksp(libs.glide.ksp)

    implementation(libs.androidx.security.core)

    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
}
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            //Built in
            library("androidx-core", "androidx.core:core-ktx:1.10.1")
            library("androidx-appCompat", "androidx.appcompat:appcompat:1.6.1")
            library("google-material", "com.google.android.material:material:1.9.0")
            library("androidx-constraintLayout", "androidx.constraintlayout:constraintlayout:2.1.4")
            library("test-junit", "junit:junit:4.13.2")
            library("androidTest-junit", "androidx.test.ext:junit:1.1.5")
            library("androidTest-espresso", "androidx.test.espresso:espresso-core:3.5.1")
            // Navigation
            library("navigation-fragment", "androidx.navigation:navigation-fragment-ktx:2.7.3")
            library("navigation-ui", "androidx.navigation:navigation-ui-ktx:2.7.3")
            // Retrofit and Okhttp
            library("retrofit2-retrofit", "com.squareup.retrofit2:retrofit:2.9.0")
            library("retrofit2-converter-gson", "com.squareup.retrofit2:converter-gson:2.9.0")
            library("okhttp3-logging-interceptor", "com.squareup.okhttp3:logging-interceptor:4.9.0")
            // Dagger Hilt
            library("dagger-hilt-core","com.google.dagger:hilt-android:2.48")
            library("dagger-hilt-compiler","com.google.dagger:hilt-android-compiler:2.48")
            // Glide
            library("glide-core", "com.github.bumptech.glide:glide:4.16.0")
            library("glide-ksp", "com.github.bumptech.glide:ksp:4.16.0")
            // EncryptedSharedPreferences
            library("androidx-security-core", "androidx.security:security-crypto:1.1.0-alpha06")
            // ViewModel and LiveData Lifecycle
            library("lifecycle-viewmodel","androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
            library("lifecycle-livedata","androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
        }
    }
}

rootProject.name = "GithubApp"
include(":app")
 
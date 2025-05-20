plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Safe Args eklentisi
    id("androidx.navigation.safeargs.kotlin")

    id("kotlin-parcelize")
}

android {
    namespace = "com.example.yemeksiparisuygulamasi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.yemeksiparisuygulamasi"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

// Retrofit & JSON dönüşümü için Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Resim yükleme kütüphanesi
    implementation("com.github.bumptech.glide:glide:4.16.0")

// ViewModel ve LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

// RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

// Material Components
    implementation("com.google.android.material:material:1.11.0")
}
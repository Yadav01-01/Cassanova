plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.bussiness.cassanova"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bussiness.cassanova"
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
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //navigation
    implementation (libs.androidx.navigation.compose)
    //splash screen
    implementation (libs.androidx.core.splashscreen)
    //Accompanist
    implementation (libs.accompanist.pager)
    implementation (libs.accompanist.pager.indicators)
    //chip
    implementation (libs.accompanist.flowlayout)
    //exoplayer
    implementation (libs.androidx.media3.exoplayer)
    implementation (libs.androidx.media3.ui)
    //countryCodePicker
//    implementation(libs.komposecountrycodepicker)
    //coil
    implementation(libs.coil.compose.v260)
    //hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    // Hilt for Jetpack Compose
    implementation (libs.androidx.hilt.navigation.compose)
    implementation (libs.accompanist.flowlayout)

    implementation(project(":KomposeCountryCodePicker"))


}
import java.util.Properties
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //Serialization plugin
    alias(libs.plugins.jetbrainsKotlinSerialization)
    //KSP plugin
    alias(libs.plugins.ksp)
    //Dagger Hilt plugin
    alias(libs.plugins.hilt)
}

val localProperties = Properties().apply {
    val localFile = rootProject.file("local.properties")
    if (localFile.exists()) {
        localFile.inputStream().use { load(it) }
    }
}

android {
    namespace = "com.ankrisdevs.android_challenge"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ankrisdevs.android_challenge"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "SPOTIFY_CLIENT_ID",
            "\"${localProperties.getProperty("SPOTIFY_CLIENT_ID") ?: ""}\""
        )
        buildConfigField(
            "String",
            "SPOTIFY_REDIRECT_URI",
            "\"${localProperties.getProperty("SPOTIFY_REDIRECT_URI") ?: ""}\""
        )
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
        freeCompilerArgs = listOf("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    //Core libs
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    //--ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //--Serialization
    implementation(libs.kotlinx.serialization.json)

    //Navigation libs
    //--Navigation
    implementation(libs.androidx.navigation.compose)

    //DI o Dependency Injection
    //--Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)

    //--KSP
    ksp(libs.hilt.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter)

    //Coil
    //--Coil Carga de imágenes
    implementation(libs.coil.compose)
    //--Coil Parte de red
    implementation(libs.coil.network.okhttp)

    //Testing libs
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.baitapvenha_ui"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.baitapvenha_ui"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        // version khớp với plugin compose compiler
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AndroidX cơ bản
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.2")

    // Jetpack Compose UI
    implementation("androidx.compose.ui:ui:1.7.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.3")

    // Material3 (nên dùng thay cho material cũ)
    implementation("androidx.compose.material3:material3:1.3.0")

    // Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.3")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Coil for Image loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Debug (Preview & Tooling)
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.3")
}

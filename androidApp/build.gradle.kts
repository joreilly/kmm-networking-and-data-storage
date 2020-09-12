plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}
group = "com.jetbrains.handson"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.core:core-ktx:1.3.1")


    implementation("androidx.compose.ui:ui:1.0.0-alpha02")
    implementation("androidx.ui:ui-tooling:1.0.0-alpha02")
    implementation("androidx.compose.foundation:foundation:1.0.0-alpha02")
    implementation("androidx.compose.material:material:1.0.0-alpha02")
}
android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.jetbrains.handson.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.0"
        kotlinCompilerExtensionVersion = "1.0.0-alpha02"
    }

    buildFeatures {
        compose = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check")
    }
}


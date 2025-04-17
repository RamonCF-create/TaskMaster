plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt") // ADICIONADO para o Room funcionar
}

android {
    namespace = "com.example.taskmaster"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.taskmaster"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation("androidx.core:core-ktx:1.12.0")


    // 🔹 Room (Banco de Dados Local)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
        implementation("androidx.appcompat:appcompat:1.6.1")        // Para AppCompatActivity
        implementation("androidx.recyclerview:recyclerview:1.3.2")  // Para RecyclerView
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2") // Para lifecycleScope
        // E os outros que você já tinha, como Room


    // 🔹 Coroutines (Execuções assíncronas)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

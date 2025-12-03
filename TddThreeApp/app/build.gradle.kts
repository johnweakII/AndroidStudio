import org.gradle.api.JavaVersion // <--- Add this line if it's missing
plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize") // Required for Step 1
}

android {
    namespace = "com.example.tddthreeapp"
    compileSdk = 35 // Or your latest SDK

    defaultConfig {
        applicationId = "com.example.tddthreeapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.example.tddthreeapp.CustomTestRunner" // We will create this later
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // This forces Kotlin compiler (21 default) to use 17
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true // For Robolectric
    }
}

dependencies {
    // --- Core Android & UI Libraries ---
    // Ensure you use the aliases defined in your libs.versions.toml for consistency.
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Explicitly keeping activity-ktx since it was specified, though often included via appcompat/core
    implementation("androidx.activity:activity-ktx:1.9.0")

    implementation(libs.androidx.constraintlayout)

    // RecyclerView and its contents (LinearLayoutManager, etc.)
    // Note: Use a single, recent version. 1.3.2 is stable.
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation(libs.androidx.runner)

    // Removing: androidx.material3, androidx.runner, androidx.espresso.contrib,
    // and androidx.recyclerview-selection unless explicitly needed for advanced UI features.
    // We only need core views and RecyclerView for this project.

    // --- Testing (Unit & Robolectric - Runs on JVM) ---
    // These must use 'testImplementation'
   // Contains AndroidJUnit4 runner

    // --- Testing (UI & Espresso - Runs on Device/Emulator) ---
    // These must use 'androidTestImplementation'

    androidTestImplementation(libs.androidx.espresso.core)

    // CRITICAL: Required for Intents.init(), intended(), and hasComponent()
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")

    // Required for synchronized testing (Idling Resource)
    implementation("androidx.test.espresso:espresso-idling-resource:3.5.1")

    // Optional but good for RecyclerView testing with Espresso (Robot 2)
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")

    testImplementation(libs.junit)
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("androidx.test.ext:junit:1.1.5")

    // ⬇️ CRITICAL ADDITIONS FOR ROBOLECTRIC INTENT TESTING ⬇️
    // These lines expose the necessary classes (onView, Intents, intended, hasComponent)
    // to the JVM test environment, which usually only happens automatically for androidTest.
    testImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    // ⬆️ CRITICAL ADDITIONS FOR ROBOLECTRIC INTENT TESTING ⬆️

    // --- Testing (UI & Espresso - Runs on Device/Emulator) ---
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Often redundant, but good to ensure match
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    implementation("androidx.test.espresso:espresso-idling-resource:3.5.1")
}
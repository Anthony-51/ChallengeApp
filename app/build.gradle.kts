plugins {
      id("com.android.application")
      id("org.jetbrains.kotlin.android")
      id("kotlin-kapt")
      id("kotlin-parcelize")
      id("com.google.devtools.ksp")
      id("com.google.dagger.hilt.android")
      id("kotlinx-serialization")
}

android {
      namespace = "com.alex.challengeapp"
      compileSdk = 34

      defaultConfig {
            applicationId = "com.alex.challengeapp"
            minSdk = 23
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                  useSupportLibrary = true
            }
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
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
      }
      kotlinOptions {
            jvmTarget = "1.8"
      }
      buildFeatures {
            compose = true
      }
      composeOptions {
            kotlinCompilerExtensionVersion = "1.5.1"
      }
      packaging {
            resources {
                  excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
      }
}

dependencies {
      testImplementation("junit:junit:4.12")
      val retrofitVersion = "2.9.0"
      val hiltVersion = "2.50"
      val navVersion = "2.7.7"
      val lifecycleVersion = "2.7.0"
      val roomVersion = "2.5.2"

      implementation("androidx.core:core-ktx:1.12.0")
      implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
      implementation("androidx.activity:activity-compose:1.8.2")
      implementation(platform("androidx.compose:compose-bom:2023.08.00"))
      implementation("androidx.compose.ui:ui")
      implementation("androidx.compose.ui:ui-graphics")
      implementation("androidx.compose.ui:ui-tooling-preview")
      implementation("androidx.compose.material3:material3")

      //dagger
      implementation("com.google.dagger:hilt-android:$hiltVersion")
      kapt("com.google.dagger:hilt-compiler:$hiltVersion")
      kapt("androidx.hilt:hilt-compiler:1.1.0")
      implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

      //Retrofit 2
      implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
      implementation("com.squareup.retrofit2:converter-scalars:$retrofitVersion")
      implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

      //coroutines
      implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

      //lifecycle
      // ViewModel
      implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
      // ViewModel utilities for Compose
      implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
      implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
      // Saved state module for ViewModel
      implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")

      //Navigation
      implementation ("androidx.navigation:navigation-compose:$navVersion")

      //Coil
      implementation("io.coil-kt:coil-compose:2.5.0")

      //serialization
      implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

      //Room
      implementation("androidx.room:room-runtime:$roomVersion")
      annotationProcessor("androidx.room:room-compiler:$roomVersion")
      // To use Kotlin Symbol Processing (KSP)
      ksp("androidx.room:room-compiler:$roomVersion")
      // optional - Kotlin Extensions and Coroutines support for Room
      implementation("androidx.room:room-ktx:$roomVersion")
      // optional - Paging 3 Integration
      implementation("androidx.room:room-paging:$roomVersion")

      // Paging
      implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
      implementation ("androidx.paging:paging-compose:1.0.0-alpha18")

      implementation ("androidx.paging:paging-compose:1.0.0-alpha18")
      // alternatively - without Android dependencies for tests
      testImplementation ("androidx.paging:paging-common:3.1.1")
      testImplementation ("androidx.paging:paging-testing:3.2.1")
      //Coroutines test
      testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
      testImplementation ("androidx.arch.core:core-testing:2.2.0")
      testImplementation ("io.mockk:mockk:1.12.2")
      // Optional -- Robolectric environment
      testImplementation ("androidx.test:core:1.5.0")
      // AndroidJUnitRunner and JUnit Rules
      testImplementation("junit:junit:4.13.2")
      testImplementation ("androidx.test:runner:1.5.2")
      testImplementation ("androidx.test:rules:1.5.0")
      androidTestImplementation ("androidx.test:runner:1.5.2")
      androidTestImplementation ("androidx.test:rules:1.5.0")
      // Optional -- Mockito framework
      testImplementation ("org.mockito:mockito-core:3.9.0")
      // Optional -- mockito-kotlin
      testImplementation ("org.mockito.kotlin:mockito-kotlin:3.2.0")
      testImplementation("junit:junit:4.13.2")
      testImplementation("androidx.test.ext:junit:1.1.5")
      testImplementation("androidx.test.ext:junit-ktx:1.1.5")
      testImplementation ("androidx.test:core-ktx:1.5.0")
      testImplementation ("org.robolectric:robolectric:4.11.1")


      testImplementation("app.cash.turbine:turbine:0.7.0")
      testImplementation("com.google.truth:truth:1.1.3")

      androidTestImplementation("androidx.test.ext:junit:1.1.5")
      androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
      androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
      androidTestImplementation("androidx.compose.ui:ui-test-junit4")
      debugImplementation("androidx.compose.ui:ui-tooling")
      debugImplementation("androidx.compose.ui:ui-test-manifest")
}

// Allow references to generated code
kapt {
      correctErrorTypes = true
}
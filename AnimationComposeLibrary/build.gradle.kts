import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.sort.dependencies)
    alias(libs.plugins.gradle.build.health)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "com.joyner.animationcomposelibrary"
    compileSdk = 36

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        languageVersion = org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3
        jvmTarget = JvmTarget.fromTarget(target = "21")
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates(
        groupId = "io.github.joyner-perez",
        artifactId = "AnimationComposeLibrary",
        version = "1.0.4"
    )

    pom {
        name.set("AnimationComposeLibrary")
        description.set("Easy implementation of animations.")
        inceptionYear.set("2020")
        url.set("https://github.com/joyner-perez/AnimationComposeLibrary")
        licenses {
            license {
                name.set("MIT")
                url.set("https://mit-license.org")
                distribution.set("https://mit-license.org")
            }
        }
        developers {
            developer {
                id.set("Joyner")
                name.set("Joyner")
                url.set("https://github.com/joyner-perez")
            }
        }
        scm {
            url.set("https://github.com/joyner-perez/AnimationComposeLibrary")
            connection.set("scm:git:git://github.com/joyner-perez/AnimationComposeLibrary.git")
            developerConnection.set(
                "scm:git:ssh://git@github.com/joyner-perez/AnimationComposeLibrary.git"
            )
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.icons.extended)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.rules.compose)
    detektPlugins(libs.detekt.rules.libraries)
    detektPlugins(libs.detekt.rules.ruleauthors)
}

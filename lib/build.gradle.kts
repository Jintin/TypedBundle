plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("signing")
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
}

android {
    namespace = "com.jintin.bundle"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}
signing {
    useInMemoryPgpKeys(
        System.getenv("OSS_SIGNING_KEY"),
        System.getenv("OSS_SIGNING_PASSWORD"),
    )
    sign(publishing.publications)
}

dependencies {
    ksp(project(":processor"))

    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.11.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.jintin"
            artifactId = "typed-bundle"
            version = "0.3.0"

            pom {
                name.set("TypedBundle")
                description.set("Type safe Bundle for Android development")
                url.set("https://github.com/Jintin/TypedBundle")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("Jintin")
                        email.set("jintin.lin1018@gmail.com")
                    }
                }
                scm {
                    connection.set("https://github.com/Jintin/TypedBundle")
                    developerConnection.set("https://github.com/Jintin/TypedBundle")
                    url.set("https://github.com/Jintin/TypedBundle")
                }
            }
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
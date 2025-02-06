import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
    id("com.vanniktech.maven.publish") version "0.29.0"
}

version = properties["VERSION_NAME"].toString()

kotlin {
    jvmToolchain(8)
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}

description = "Extensions pack for coroutines"

publishing.publications
    .withType<MavenPublication>()
    .configureEach {
        groupId = "io.github.arlanchikdrey"

        pom {
            name.set("CoroutineExt")
            description.set(project.description)
            url.set("https://github.com/ArlanchikDrey/CoroutineExt")

            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("repo")
                }
            }

            developers {
                developer {
                    id.set("ArlanchikDrey")
                    url.set("https://github.com/ArlanchikDrey")
                }
            }

            issueManagement {
                system.set("GitHub")
                url.set("https://github.com/ArlanchikDrey/CoroutineExt/issues")
            }

            scm {
                connection.set("scm:git:git://github.com/ArlanchikDrey/CoroutineExt.git")
                developerConnection.set("scm:git:ssh://github.com/ArlanchikDrey/CoroutineExt.git")
                url.set("https://github.com/ArlanchikDrey/CoroutineExt")
            }
        }
    }

publishing {
    repositories {
        mavenLocal()

        maven {
            name = "BuildDir"
            url = uri(rootProject.layout.buildDirectory.dir("maven-repo"))
        }
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = false)
    signAllPublications()
}
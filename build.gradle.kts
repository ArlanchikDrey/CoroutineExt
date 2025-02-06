plugins {
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
}

version = properties["VERSION_NAME"].toString()

java {
    withSourcesJar()
    withJavadocJar()
}

kotlin {
    jvmToolchain(8)
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}

description = "Extensions pack for coroutines"

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])

            groupId = "net.arlantech"
            artifactId = "coroutineext"

            pom {
                name.set("CoroutineExt")
                description.set(project.description)
                url.set("https://github.com/ArlanchikDrey/CoroutineExt")

                issueManagement {
                    url.set("https://github.com/ArlanchikDrey/CoroutineExt/issues")
                }

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
            }
        }
    }

    repositories {
        mavenLocal()

        maven {
            name = "BuildDir"
            url = uri(rootProject.layout.buildDirectory.dir("maven-repo"))
        }
    }
}
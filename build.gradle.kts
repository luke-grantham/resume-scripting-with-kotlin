plugins {
    kotlin("jvm") version "1.9.21"
    //kotlin("jvm") version "1.8.21"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "tech.lukegrantham"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
}


// https://www.baeldung.com/kotlin/gradle-dsl
// https://kotlinlang.org/docs/gradle-configure-project.html#dependency-types
dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("com.itextpdf:itext-core:8.0.2") // https://repo.maven.apache.org/maven2/com/itextpdf/itext-core/
    implementation("com.itextpdf:bouncy-castle-adapter:8.0.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}


tasks.shadowJar.configure {
    archiveClassifier.set("")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifact(tasks["shadowJar"])
        }
    }
    repositories {
        mavenLocal()
    }
}

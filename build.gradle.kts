plugins {
    kotlin("jvm") version "1.9.21"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "tech.lukegrantham"
version = "1.0"

repositories {
    mavenCentral()
    mavenLocal()
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

/*shadowJar {
    baseName = 'myproject-shadow'
    classifier = ''
}*/

/*publishing {
    publications {
        shadow(MavenPublication) {
            from components.shadow
                    artifactId = 'myproject-shadow'
        }
    }
}*/

publishing {
    publications {
        create<MavenPublication>("maven") {
            //from(components["shadow"])
            artifact(tasks["shadowJar"])

        }
    }
    repositories {
        mavenLocal()
    }
}

/*publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}*/

/*
tasks.named('shadowJar', ShadowJar) {
    enableRelocation true
}*/

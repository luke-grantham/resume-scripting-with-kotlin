plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// https://www.baeldung.com/kotlin/gradle-dsl
// https://kotlinlang.org/docs/gradle-configure-project.html#dependency-types
dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("com.itextpdf:itext-core:8.0.2")// https://repo.maven.apache.org/maven2/com/itextpdf/itext-core/
    implementation("com.itextpdf:bouncy-castle-adapter:8.0.2")
    //implementation("com.itdextpdf:iteasdfasdfxt-core:8.0.2")
    //implementation("com.itextpdf:bouncy-castle-adapter:8.0.2")
    //compile("com.itdextpdf:iteasdfasdfxt-core:5.5.13.3")
    //compile("com.itextpdf:bouncy-castle-adapter:5.5.13.3")
    //compile("com.itextpdf:itext-core:8.0.2")
    //compile("com.itextpdf:bouncy-castle-adapter:8.0.2")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}
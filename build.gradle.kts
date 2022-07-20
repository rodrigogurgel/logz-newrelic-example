import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("de.undercouch.download") version "3.4.3"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "br.com.rodrigogurgel"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("io.logz.logback:logzio-logback-appender:1.0.27")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.newrelic.agent.java:newrelic-api:7.8.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

springBoot {
    buildInfo()
}

tasks.register<de.undercouch.gradle.tasks.download.Download>("downloadNewrelic") {
    mkdir("newrelic")
    src("https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip")
    dest(file("newrelic"))
}

tasks.register<Copy>("unzipNewrelic") {
    from(zipTree(file("newrelic/newrelic-java.zip")))
    into(rootDir)
}

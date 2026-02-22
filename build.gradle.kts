plugins {
	kotlin("jvm") version "2.1.10"
	kotlin("plugin.spring") version "2.1.10"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "2.1.10"
	`java-library`
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.boot:spring-boot-dependencies:3.2.5")
	}
}

group = "com.github.kndevelop"
version = "v1.0.1"
description = "Spring common Api"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	api("org.springframework.boot:spring-boot-starter-security")
	api("org.springframework.boot:spring-boot-starter-validation")
	api("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
	implementation("org.xerial:sqlite-jdbc")
	implementation("org.hibernate.orm:hibernate-community-dialects")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-security-test")
	testImplementation("org.springframework.boot:spring-boot-starter-validation-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
	enabled = true
}
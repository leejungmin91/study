import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.4"
	id("io.spring.dependency-management") version "1.1.4"

	val kotlinVersion = "1.8.22"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
	kotlin("kapt") version kotlinVersion
	//idea
}

repositories {
	mavenCentral()
}

java.sourceCompatibility = JavaVersion.VERSION_17


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	//implementation("org.jetbrains.kotlin:kotlin-reflect")
	//implementation(kotlin("stdlib"))
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.24")  // 명확한 Kotlin stdlib 버전 사용
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")

	// QueryDSL
	implementation("com.querydsl:querydsl-jpa:4.4.0")
	kapt("com.querydsl:querydsl-apt:4.4.0:jpa")
	kapt("com.google.dagger:dagger-compiler:2.44")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
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

tasks.getByName<Jar>("jar") {
	enabled = false
}

/*idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}*/

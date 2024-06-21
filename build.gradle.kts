import java.time.Duration
import java.time.Instant

plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

//task 3
tasks.register("checkGameJsonFile") {
    doLast {
        val resourceDir = file("game/src/main/resources")
        val jsonFile = File(resourceDir, "game.json")

        if (jsonFile.exists()) {
            println("File game.json exists")
        } else {
            println("File game.json does not exist")
        }
    }
}

val startTime = Instant.now()

//task 4
tasks.register("generateBuildReport") {
    doLast {
        val endTime = Instant.now()
        val duration = Duration.between(startTime, endTime)

        val reportContent = StringBuilder()
        reportContent.append("Build Report\n")
        reportContent.append("============\n")
        reportContent.append("Build Duration: ${duration.toMillis()} ms\n")

        val reportDir = file("build/reports")
        if (!reportDir.exists()) {
            reportDir.mkdirs()
        }

        val reportFile = File(reportDir, "buildReport.txt")
        reportFile.writeText(reportContent.toString())
        println(reportContent.toString())

        println("Build report generated at: ${reportFile.absolutePath}")
    }
}


tasks.named("build") {
    finalizedBy("checkGameJsonFile")
    finalizedBy("generateBuildReport")
}
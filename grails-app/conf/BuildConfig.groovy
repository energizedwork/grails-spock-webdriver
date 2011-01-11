grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.dependency.resolution = {
    inherits "global"
    log "warn"
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        compile("org.seleniumhq.selenium:selenium-firefox-driver:2.0b1")
        test("org.seleniumhq.selenium:selenium-chrome-driver:2.0b1")
        test("org.seleniumhq.selenium:selenium-ie-driver:2.0b1")
        test("org.seleniumhq.selenium:selenium-htmlunit-driver:2.0b1") {
            excludes "xml-apis"
        }
        compile("org.seleniumhq.selenium:selenium-support:2.0b1") {
            excludes "junit", "hamcrest-all"
        }
    }
    plugins {
        compile ":spock:0.5-groovy-1.7-SNAPSHOT"
    }
}

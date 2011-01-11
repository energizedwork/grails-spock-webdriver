class SpockWebdriverGrailsPlugin {

	def groupId = "com.energizedwork"
    def version = "1.0.3-SNAPSHOT"
    def grailsVersion = "1.2.0 > *"
    def dependsOn = [:]
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Energized Work"
    def authorEmail = "plugins@energizedwork.com"
    def title = "Spock WebDriver"
    def description = '''\\
Facilitates functional testing using Spock specifications and WebDriver
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/spock-webdriver"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}

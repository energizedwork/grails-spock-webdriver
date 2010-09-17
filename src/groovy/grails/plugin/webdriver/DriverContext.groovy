package grails.plugin.webdriver

import grails.build.GrailsBuildListener
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import grails.util.*

class DriverContext implements GrailsBuildListener {

	@Lazy static WebDriver driver = initDriver()
	@Lazy static ConfigObject config = initConfig()
	
	private static boolean closeAfterTest = true

	static void closeDriver() {
		if (closeAfterTest) {
			driver?.close()
		}
	}

	void receiveGrailsBuildEvent(String name, Object... args) {
		if (name == "TestPhaseStart" && args[0] == "functional") {
			closeAfterTest = false
		} else if (name == "TestPhaseEnd" && args[0] == "functional") {
			driver?.close()
		}
	}

	private static ConfigObject initConfig() {
		def baseDir = BuildSettingsHolder.settings?.baseDir ?: new File(".")
		def configFile = new File(baseDir, "grails-app/conf/WebDriverConfig.groovy")
		if (configFile.isFile()) {
			println "parsing config from $configFile.absolutePath..."
			new ConfigSlurper(GrailsUtil.environment).parse(configFile.toURI().toURL())
		} else {
			println "no WebDriverConfig.groovy found at $configFile.absolutePath..."
			null
		}
	}

	private static WebDriver initDriver() {
		driverType.newInstance()
	}

	private static Class<? extends WebDriver> getDriverType() {
		def driverClassName = config?.webdriver?.driver
		driverClassName ? Class.forName(driverClassName) : FirefoxDriver
	}

}

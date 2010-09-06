package grails.plugin.webdriver

import grails.build.GrailsBuildListener
import grails.util.GrailsUtil
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import grails.util.BuildSettingsHolder
import groovy.transform.Synchronized

class DriverContext implements GrailsBuildListener {

	private static WebDriver driver

	@Synchronized
	static WebDriver getDriver() {
		if (!driver) {
			driver = driverType.newInstance()
		}
		driver
	}

	@Lazy static ConfigObject config = {
		def baseDir = BuildSettingsHolder.settings?.baseDir ?: new File(".")
		def configFile = new File(baseDir, "grails-app/conf/WebDriverConfig.groovy")
		if (configFile.isFile()) {
			println "parsing config from $configFile.absolutePath..."
			new ConfigSlurper(GrailsUtil.environment).parse(configFile.toURI().toURL())
		} else {
			println "no WebDriverConfig.groovy found..."
			null
		}
	}()

	void receiveGrailsBuildEvent(String name, Object... args) {
		if (name == "TestPhaseStart" && args[0] == "functional") {
			driver = driverType.newInstance()
		} else if (name == "TestPhaseEnd" && args[0] == "functional") {
			driver.close()
		}
	}

	private static Class<? extends WebDriver> getDriverType() {
		def driverClassName = config?.webdriver?.driver
		driverClassName ? Class.forName(driverClassName) : FirefoxDriver
	}
}

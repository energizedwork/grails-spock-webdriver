eventAllTestsStart = {
	def specTestTypeClass = loadSpecTestTypeClass()
	if (argsMap.noStart) {
		event "StatusUpdate", ["Running functional tests without starting the app"]
		functionalTestPhasePreparation = otherTestPhasePreparation
		functionalTestPhaseCleanUp = otherTestPhaseCleanUp
	}
	functionalTests << specTestTypeClass.newInstance("spock", "functional")
}

eventTestPhasesStart = {
	eventListener.addGrailsBuildListener("grails.plugin.webdriver.DriverContext")
}

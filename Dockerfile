FROM openjdk:8-jre-slim

# Add the jar with all the dependencies
# Maven creates container-test.jar in the target folder of my workspace.
# We need this in some location - say - /usr/share/tag folder of the container
ADD  target/STARTBrowser_Automation-0.0.1.jar  /usr/share/tag/STARTBrowser_Automation-0.0.1.jar
# Command line to execute the test
ENTRYPOINT ["/usr/bin/java", "-cp", "/usr/share/tag/container-test.jar", "org.testng.TestNG", "-testclass", "/src/test/java/pages/HomePageTest.java"]
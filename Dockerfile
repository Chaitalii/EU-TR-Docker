FROM  ubuntu
RUN mkdir dockertest
RUN cd dockertest
RUN apt-get update
RUN apt-get install -y  default-jdk
RUN apt-get install -y testng
RUN apt-get install -y maven
#RUN set classpath=/code/EU-TR-Docker/target/STARTBrowser_Automation-0.0.1.jar:/code/EU-TR-Docker/target/classes:/root/.m2/repository/org/testng/testng/6.14.3/testng-6.14.3.jar: /root/.m2/repository/org/beanshell/bsh/2.0b4/bsh-2.0b4.jar:/root/.m2/repository/com/beust/jcommander/1.72/jcommander-1.72.jar
WORKDIR /dockertest
COPY  src  /dockertest
#ADD   /code/EU-TR-Docker/ .
#COPY code/EU-TR-Docker/src .


COPY STARTBrowser_Automation-0.0.1.jar /dockertest
COPY classes /dockertest
COPY testng-6.14.3.jar /dockertest
COPY bsh-2.0b4.jar /dockertest
COPY jcommander-1.72.jar /dockertest
COPY src/test/resources/testng.xml /dockertest
COPY pom.xml /dockertest
#ADD /root/code/EU-TR-Docker/libs /target/libs

# Add the jar with all the dependencies
# Maven creates container-test.jar in the target folder of my workspace.
# We need this in some location - say - /usr/share/tag folder of the container
#ADD  target/STARTBrowser_Automation-0.0.1.jar  /code/EU-TR-Docker/
# Command line to execute the test
#CMD ["java", "-cp", "/target/STARTBrowser_Automation-0.0.1.jar", "/code/TourRadar-EU/target/classes"]
RUN mvn clean install -U
RUN mvn test
ENTRYPOINT ["/bin/bash"]
#ENTRYPOINT mvn package

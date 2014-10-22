Buildout of ideas from Modern Java Series

http://blog.paralleluniverse.co/2014/05/01/modern-java/
http://blog.paralleluniverse.co/2014/05/08/modern-java-pt2/
http://blog.paralleluniverse.co/2014/05/15/modern-java-pt3/

Contains:

* Gradle based web service
* Based on DropWizard
* Uses dagger for Dependency Injection
* Uses jdbi for database interaction
* Uses jolokia for monitoring

java -Ddropwizard.config=./src/main/resources/appconfig.yml -jar ./build/libs/dropwizard-gradle-archetype-0.1.0-fatJar.jar


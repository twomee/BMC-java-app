# Build Stage
FROM maven:3.8-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Execution Stage
FROM openjdk:17-oracle
COPY --from=build /home/app/target/bmcApp-1.0-SNAPSHOT.jar /usr/local/lib/bmcApp-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/bmcApp-1.0-SNAPSHOT.jar"]


#to build a java dockerfile:
#1. add to pom the spring boot maven plugin
#2. add the mainClass where the app start to run
#3. in the properties section, add start-class attribute which is also the main class to run
# this is for creating a correct MANIFEST file that contain the main class
#4. run the command: mvn package spring-boot:repackage
# https://www.baeldung.com/spring-boot-fix-the-no-main-manifest-attribute
# https://stackoverflow.com/questions/73546442/best-practice-for-dockerizing-a-springboot-app-in-a-cicd-pipeline

#to run it: sudo docker build -t app:latest .
FROM java:8
EXPOSE 8081
LABEL authors="Administrator"
ADD ./SimpleMI-1.0.0.jar /SimpleMI-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/SimpleMI-1.0.0.jar", "--spring.profiles.active=dev"]
MAINTAINER careshadow
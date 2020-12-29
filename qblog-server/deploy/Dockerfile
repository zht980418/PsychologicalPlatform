FROM java:8
ADD qblog-api.jar /qblog-api.jar
COPY ./_qblog /_qblog
ENTRYPOINT ["java", "-jar","/qblog-api.jar","--spring.profiles.active=pro"]
FROM openjdk:8
ADD target/LaEstufaApplication.war LaEstufaApplication.war
EXPOSE 7878
ENTRYPOINT ["java", "-jar", "LaEstufaApplication.war"]
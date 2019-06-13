FROM openjdk:11-jdk
MAINTAINER Ray Cheung <clojure@pragmatic.hk>

ADD target/uberjar/{{name}}.jar /{{name}}/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/{{name}}/app.jar"]

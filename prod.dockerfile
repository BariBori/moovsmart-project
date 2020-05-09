FROM azul/zulu-openjdk-alpine:11.0.7
COPY target/project-1.0-SNAPSHOT.jar /backend/
WORKDIR /backend
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=development
ENTRYPOINT [ "/usr/lib/jvm/zulu11-ca/bin/java" ]
CMD [ "-Xmx1024m", "-jar", "target/project-1.0-SNAPSHOT.jar"
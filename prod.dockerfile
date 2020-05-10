FROM azul/zulu-openjdk-alpine:11.0.7
COPY target/project-1.0-SNAPSHOT.jar /backend/
WORKDIR /backend
EXPOSE 8080
ENTRYPOINT [ "/usr/lib/jvm/zulu11-ca/bin/java", "-jar", "project-1.0-SNAPSHOT.jar" ]
CMD [ "-Xmx1024m" ]
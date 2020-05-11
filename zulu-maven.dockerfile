# same JDK we develop on on a lightweight Linux image
FROM azul/zulu-openjdk-alpine:11.0.7

ENV MAVEN_VERSION 3.6.3
ENV MAVEN_HOME /usr/lib/mvn
ENV PATH $MAVEN_HOME/bin:$PATH

# install maven
RUN wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz\
    && tar -zxvf apache-maven-$MAVEN_VERSION-bin.tar.gz\
    && rm apache-maven-$MAVEN_VERSION-bin.tar.gz\
    && mv apache-maven-$MAVEN_VERSION /usr/lib/mvn
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
COPY pom.xml /backend/
WORKDIR /backend
# download maven dependencies
RUN mvn dependency:resolve
RUN mvn dependency:resolve-plugins
RUN mvn resources:resources
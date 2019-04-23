FROM gradle:jdk8-alpine as builder
COPY ./ /src
WORKDIR /src
USER root
RUN chown -R gradle:gradle /src
USER gradle
RUN gradle build

FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS=""

# Add a jhipster user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh jhipster
WORKDIR /home/jhipster

ADD docker/entrypoint.sh entrypoint.sh
COPY --from=builder "/src/build/libs/*.war" app.war

RUN chmod 755 entrypoint.sh && chown jhipster:jhipster entrypoint.sh
USER jhipster

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080
#

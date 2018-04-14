FROM openjdk:8-jre

RUN mkdir -p /srv

ADD build/libs/adam-service.jar /srv/

ENTRYPOINT java -jar /srv/adam-service.jar
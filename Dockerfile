FROM jeanblanchard/busybox-java:8
MAINTAINER greg@greglook.net

# Environment settings.
ENV PORT 8080
ENV APP_ROOT /srv

# Add service code and configuration.
#COPY config.edn $APP_ROOT/config.edn
COPY target/uberjar/body-app-*-standalone.jar $APP_ROOT/app.jar

# Container settings and command.
WORKDIR $APP_ROOT
EXPOSE $PORT
USER daemon

CMD ["java", "-jar", "/srv/app.jar"]

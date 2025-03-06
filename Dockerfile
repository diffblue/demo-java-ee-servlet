FROM maven:3.8.6-openjdk-8-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM tomcat:9.0-jre8-temurin
# Tomcat Docker Hub page (https://hub.docker.com/_/tomcat) has this warning:
#     As of docker-library/tomcat#181 (https://github.com/docker-library/tomcat/pull/181)⁠, the upstream-provided (example)
#     webapps are not enabled by default, per upstream's security recommendations.
#     (https://tomcat.apache.org/tomcat-9.0-doc/security-howto.html#Default_web_applications)⁠,
#     These apps are still available under the webapps.dist folder within the image to make them easier to re-enable.
# So we move some folders around to re-enable the default behaviour
RUN mv /usr/local/tomcat/webapps /usr/local/tomcat/webapps.empty
RUN mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps
COPY --from=build /app/target/DemoServlet.war /usr/local/tomcat/webapps/

# Enable manager mode
COPY --from=build /app/tomcat/manager/context.xml /usr/local/tomcat/webapps/manager/META-INF/
COPY --from=build /app/tomcat/tomcat-users.xml /usr/local/tomcat/conf/

# Enable examples access from remote host
COPY --from=build /app/tomcat/examples/context.xml /usr/local/tomcat/webapps/examples/META-INF/

# Logging
COPY --from=build /app/tomcat/logging.properties /usr/local/tomcat/conf/

EXPOSE 8080
CMD ["catalina.sh", "run"]

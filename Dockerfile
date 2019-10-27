FROM maven:3.5-jdk-8 AS build 
COPY src /usr/app/src  
COPY pom.xml /usr/app
WORKDIR /usr/app
VOLUME /usr/app
RUN mvn package

FROM gcr.io/distroless/java
ENV VERSION 0.0.1
VOLUME /usr/app
COPY --from=build /usr/app/target/inventory-service-$VERSION.jar /usr/app/inventory-service-$VERSION.jar  
EXPOSE 6500  
ENTRYPOINT ["java","-jar","/usr/app/inventory-service-$VERSION.jar"]

HEALTHCHECK --interval=1m --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:6500/getInventoryForShop/fruitShop || exit 1
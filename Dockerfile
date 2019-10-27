FROM maven:3.5-jdk-8 AS build 
COPY src /usr/app/src  
COPY pom.xml /usr/app
WORKDIR /usr/app 
RUN mvn package

FROM gcr.io/distroless/java
ENV VERSION 0.0.1
COPY --from=build /usr/app/target/inventory-service-$VERSION-SNAPSHOT.jar /usr/app/inventory-service-$VERSION-SNAPSHOT.jar  
EXPOSE 6500  
ENTRYPOINT ["java","-jar","/usr/app/inventory-service-$VERSION-SNAPSHOT.jar"]

HEALTHCHECK --interval=1m --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:6500/getInventoryForShop/fruitShop || exit 1
FROM maven:3.5-jdk-8 AS build 
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java
ENV VERSION 0.0.1
COPY --from=build /usr/src/app/target/inventory-service-$VERSION-SNAPSHOT.jar /usr/app/inventory-service-$VERSION-SNAPSHOT.jar  
EXPOSE 6500  
ENTRYPOINT ["java","-jar","/usr/app/inventory-service-$VERSION-SNAPSHOT.jar"]

HEALTHCHECK --interval=1m --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:6500/getInventoryForShop/fruitShop || exit 1
export instanceId=book-store-service
export APP_VERSION=0.0.1-SNAPSHOT
export BOOK_STORE_DATASOURCE=-Dspring.datasource.url=jdbc:mysql://localhost:3306/BOOK_STORE -Dspring.datasource.username=root -Dspring.datasource.password=root -Dhibernate.dialect=org.hibernate.dialect.MySQLDialect

cd ../book-store-deploy/target

java -jar -Xms256m -Xmx512m -Dspring.mvc.throw-exception-if-no-handler-found=true \
-Dserver.port=8080 $BOOK_STORE_DATASOURCE \
-Dlogging.config=../../scripts/logback-spring.xml \
book-store-deploy-$APP_VERSION.jar
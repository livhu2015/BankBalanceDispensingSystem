mvn clean package -U
docker login
#docker build -t balance-dispense-api:latest .
docker build -f Dockerfile -t balance-dispense-api  .
docker push balance-dispense-api:latest

##docker run -d -t --name balance-dispense-docker --link mybatis-mysql:mysql -p 8089:8080 -e DATABASE_HOST=mybatis-mysql -e DATABASE_PORT=3306 -e DATABASE_NAME=mybatis -e DATABASE_USER=root -e DATABASE_PASSWORD=password discoverybank/balance-dispense

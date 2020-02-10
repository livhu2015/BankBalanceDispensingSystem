docker stop $(docker ps -a -q)
docker rm -f $(docker ps -aq)

#docker container stop $(docker container ls -aq)
#docker container rm $(docker container ls -aq)
#docker image rm $(docker image ls)

mvn clean package -U
docker login
docker build -t balance-dispense-api:latest .
docker push balance-dispense-api:latest

docker run -d -p 3306:3306 --name mysql-docker --env="MYSQL_ROOT_PASSWORD=password" --env="MYSQL_PASSWORD=password" --env="MYSQL_DATABASE=demo_db" mysql:latest
docker run -d -t --name balance-dispense-api --link mysql-docker:mysql -p 8089:8089 -e DATABASE_HOST=mysql-docker -e DATABASE_PORT=3306 -e DATABASE_NAME=demo_db -e DATABASE_USER=root -e DATABASE_PASSWORD=password balance-dispense-api
#docker run -t --link mysql-docker:mysql -p 8089:8089 ovalleaf01/balance-dispense-api
#docker run -p 8089 --name balance-dispense-api --link mysql-docker:mysql -d balance-dispense-api

docker container ls -a

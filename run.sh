docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

docker run -d \
    -p 2012:3306 \
     --name mysql-docker-container \
     -e MYSQL_ROOT_PASSWORD=password \
     -e MYSQL_DATABASE=demo_db \
     -e MYSQL_USER=livhu \
     -e MYSQL_PASSWORD=password \
      mysql:latest

#mvn clean install docker:build
#docker run -d -p 6033:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=book_manager" mysql

#docker run -d -p 6033:3306 --name=mybatis-mysql --env="MYSQL_ROOT_PASSWORD=password"  --env="MYSQL_USER=livhu" --env="MYSQL_DATABASE=demo" --env="MYSQL_PASSWORD=password" mysql:latest
#docker run -it --link mybatis-mysql:mysql --rm mysql sh  -c 'exec mysql -h "$MYSQL_PORT_3306_TCP_ADDR" -P "$MYSQL_PORT_3306_TCP_PORT" -u root -p "$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
##docker run -d -t --name balance-dispense-docker --link mybatis-mysql:mysql -p 8089:8080 -e DATABASE_HOST=mybatis-mysql -e DATABASE_PORT=3306 -e DATABASE_NAME=mybatis -e DATABASE_USER=root -e DATABASE_PASSWORD=password discoverybank/balance-dispense
#docker-compose down
#docker-compose up --build
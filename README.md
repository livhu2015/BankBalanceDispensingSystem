Springboot + mySql in Docker
list of docker images
- docker images

if you donot have a mysql image
- docker pull mysql:<latest-version>

set up the database the database image
- docker run -d -p 6033:3306 --name mysql-docker --env="MYSQL_ROOT_PASSWORD=password" --env="MYSQL_PASSWORD=password" --env="MYSQL_DATABASE=demo_db" mysql:latest

list all the running container
docker container ls

-------------------
Springboot app image
- To be continued

Anyways, just execute a bash file 'run-docker.sh'

http://localhost:8089/swagger-ui.html




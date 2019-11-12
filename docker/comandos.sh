sudo -s

cd ~/work/packback

git pull

export JAVA_HOME=/home/diegommagdaleno/tools/jdk-12.0.1+12
export JAVA_HOME=/home/dmagdaleno/tools/jdk-12.0.2+10

docker build -t pg-packback-db:v0.0.3 docker/db/

./gradlew clean build

cp build/libs/packback*.jar docker/packback.jar

docker build -t packback:v0.0.4 -t packback:latest docker/

docker-compose -f docker/docker-compose.yaml down

docker-compose -f docker/docker-compose.yaml up -d

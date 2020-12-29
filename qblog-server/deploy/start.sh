cd ..
# mvn clean package -Dmaven.test.skip=true
cp target/qblog-server-1.0-SNAPSHOT.jar deploy/qblog-api.jar
cp -r _qblog deploy/_qblog
cd deploy
docker build -t qblog-api:v1.0.0 .
rm -f qblog-api.jar
rm -rf _qblog
docker-compose -p qblog-api-service up -d

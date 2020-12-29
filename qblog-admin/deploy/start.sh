cd ..
# npm install
# npm run build:prod
cd deploy
cp -r ../dist ./dist
docker build -t qblog-admin:v1.0.0 .
rm -rf dist
docker-compose -p qblog-admin-service up -d

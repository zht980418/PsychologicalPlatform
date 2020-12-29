cd ..
# npm install
# npm run build
cd deploy
cp -r ../dist ./dist
docker build -t qblog-page:v1.0.0 .
rm -rf dist
docker-compose -p qblog-page-service up -d

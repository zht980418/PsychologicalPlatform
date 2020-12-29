# auto deploy the project

# install the environment
# yum install git
# yum install -y epel-release
# yum install -y nodejs
# yum install -y npm
# npm config set registry https://registry.npm.taobao.org/
# npm install -g @vue/cli
# yum -y install java
# wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
# yum -y install apache-maven

# clone and cd the project
# rm -rf QBlog2
# git clone https://gitee.com/qianyucc/QBlog2.git
# cd QBlog2
# chmod 777 start-deploy.sh
# ./start-deploy.sh

# package apis
cd qblog-server/deploy
chmod 777 start.sh
./start.sh
# package admin
cd ../../qblog-admin/deploy
chmod 777 start.sh
./start.sh
# package blog
cd ../../qblog-client/deploy
chmod 777 start.sh
./start.sh

# 前置 nginx 配置
# yum install nginx -y
# service nginx start
# cp -f qblog-server/deploy/nginx.conf /etc/nginx/nginx.conf
# nginx -s reload

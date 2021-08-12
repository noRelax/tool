#!/usr/bin/env bash

APP_PATH=/storage/zjxw-apps
Z=/storage/zjxw.sh
MVN=/usr/local/maven/bin/mvn
GIT_REPO="http://zj:123456789@10.10.112.91/zjxw/zjxw-server.git"
GIT_PATH=${APP_PATH}/zjxw-server
APPS=("task" "schedule" "admin" "api" "gateway" "dbsync" "mlfsync")
SERVER=$1
BRANCH=5.0.0

check(){
    if [ ! -d "$APP_PATH" ];then
        echo "Cannot execute dp command.Please make use 'dp init' before you do anything."
        exit 1
    fi
}

dp_init(){
    echo "Initializing the necessary files for the dp command..."
    if [ ! -d "$APP_PATH" ];then
        echo "The folder $APP_PATH does not exist! Creating $APP_PATH..."
        mkdir ${APP_PATH}
    fi
    if [ ! -d "$GIT_PATH" ];then
    	echo "Checking out from Git repository..."
    	sleep 4s
    	cd ${APP_PATH}
    	git clone ${GIT_REPO}
    fi
    cd ${GIT_PATH}/libs
    git checkout ${BRANCH}
    ${GIT_PATH}/libs/install.sh
}

if [ -z "$SERVER" ];then
    echo "No Action!"
    exit 0
fi

if [ "$SERVER" == "init" ];then
    dp_init
    exit 0
fi

check

if echo "${APPS[@]}" | grep -w "$SERVER" &>/dev/null; then
    cd ${GIT_PATH}
    git pull
    echo "Packaging the zjxw-$SERVER.jar file (mvn package)..."
    ${MVN} package -pl zjxw-${SERVER} -am
    mv ${GIT_PATH}/zjxw-${SERVER}/target/zjxw-${SERVER}.jar ${APP_PATH}
    ${Z} restart ${SERVER}
    exit 0
fi

if [ "$SERVER" == "all" ];then
    echo "Deploying all the package..."
    cd ${GIT_PATH}
    git pull
    ${MVN} clean package
    for APP in ${APPS[@]};
    do
        ${Z} stop ${APP}
        mv ${GIT_PATH}/zjxw-${APP}/target/zjxw-${APP}.jar ${APP_PATH}
        ${Z} start ${APP}
    done
    echo "** All Models are deployed successfully..."
    exit 0
fi

if [ "$SERVER" == "help" ];then
    echo "Zjxw project package & deployment tool usage. Made By LS"
    echo "The command dp provide two ways to package and deploy web apps."
    echo ''
    echo "You just have to commit your code to git,the tool will check out the latest revision from git server then package & deploy to the application server."
    echo "For example:"
    echo "dp admin             ---package and deploy admin"
    echo "dp admin && dp api   ---package and deploy admin,api"
    echo "dp all               ---package and deploy admin,api,portal,task,schedule"
    echo "dp init              ---Initialize the files needed by dp command"
    exit 0;
fi
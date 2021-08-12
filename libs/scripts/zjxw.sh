#!/usr/bin/env bash
usage() {
    echo "Usage: ${0##*/} (start|stop|restart|check|log|vim) (api|admin|task|schedule|gateway|monitor|mlfsync) (dev|test|prev|prod)"
    exit 1
}

ACTION=$1
SERVER=$2
PROFILE="dev"

[ $# -gt 1 ] || usage

APP_PATH="/storage/zjxw-apps"
LOG_PATH="/storage/zjxw-logs/$SERVER.log"
PROGRAM_ARGS="--spring.profiles.active=$PROFILE --spring.application.name=zjxw-$SERVER --logging.file=$LOG_PATH"

PID=

case ${SERVER} in
    admin)
        PORT=25000
        DEBUG_PORT=25001
        JAVA_OPTIONS="-Xmx1024m"
    ;;
    api)
        PORT=25500
        DEBUG_PORT=25501
        JAVA_OPTIONS="-Xmx2048m"
    ;;
    task)
        PORT=26000
        DEBUG_PORT=26001
        JAVA_OPTIONS="-Xmx2048m"
    ;;
    schedule)
        PORT=26500
        DEBUG_PORT=26501
        JAVA_OPTIONS="-Xmx2048m"
    ;;
    gateway)
        PORT=27000
        DEBUG_PORT=27001
        JAVA_OPTIONS="-Xmx2048m"
    ;;
    dbsync)
        PORT=27500
        DEBUG_PORT=27501
        JAVA_OPTIONS="-Xmx2048m"
    ;;
     mlfsync)
        PORT=28000
        DEBUG_PORT=28001
        JAVA_OPTIONS="-Xmx2048m"
    ;;
    migration)
        PORT=10000
        DEBUG_PORT=10050
        JAVA_OPTIONS="-Xmx2048m"
    ;;
    *)
        usage
    ;;
esac

PROGRAM_ARGS="--server.port=$PORT $PROGRAM_ARGS"

get_pid(){
    PID=`ps -ef | grep -v grep | grep zjxw-${SERVER} | awk '{print $2}'`
}

cat_start(){
    get_pid
    if [[ -n ${PID} ]] ;
    then
        echo -e "\033[41;37m  ERROR: App $SERVER is still RUNNING. PID:$PID \033[0m"
        exit 1
    fi

    if [ ${DEBUG_PORT} ];
    then
        export JAVA_OPTS="-server -Dim4java.useGM=true $JAVA_OPTIONS -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT"
    else
        export JAVA_OPTS="-server -Dim4java.useGM=true $JAVA_OPTIONS"
    fi

    echo -e "\033[32m  App $SERVER is starting: java $JAVA_OPTS -jar zjxw-$SERVER.jar $PROGRAM_ARGS \033[0m"
    nohup java ${JAVA_OPTS} -jar ${APP_PATH}/zjxw-${SERVER}.jar ${PROGRAM_ARGS} > /dev/null 2>&1 &
    #su - zjxw -c "java ${JAVA_OPTS} -jar ${APP_PATH}/zjxw-${SERVER}.jar ${PROGRAM_ARGS}"
    get_pid
    echo -e "\033[32m  App $SERVER started now. PID:$PID \033[0m"
    #tail -f "$LOG_PATH"
}
cat_stop(){
    get_pid
    if [[ -n ${PID} ]] ;
    then
        echo -e "\033[31m  App $SERVER[PID:$PID] is stopping... \033[31m"
        curl -connect-timeout 2 -m 2  -s -X POST --referer http://localhost:${PORT} http://localhost:${PORT}/internal/shutdown
        echo -e ""
        echo -e "\033[31m  App $SERVER[PID:$PID] stopped. \033[0m"
    else
        echo -e "\033[31m  WARN: App $SERVER is not running. \033[31m"
    fi
}
cat_restart(){
    #if [[ -n ${PROFILE} ]] ;
    #then
    #    echo "\033[41;37m  ERROR: Profile is not specified! \033[0m"
    #    exit 1
    #fi
    echo -e "\033[33m  App $SERVER is restarting... \033[0m"
    cat_stop 

    get_pid
    if [[ -n ${PID} ]] ;
    then
        kill $PID
        sleep 2.5
    fi
    
    get_pid
    if [[ -n ${PID} ]] ;
    then
        kill $PID
        sleep 2
    fi

    get_pid
    if [[ -n ${PID} ]] ;
    then
        echo -e "\033[41;37m  ERROR: Force to shutdown $SERVER. PID:$PID \033[0m"
        kill -9 $PID
    fi
    cat_start
    echo -e "\033[33m  App $SERVER restarted. \033[0m"
    echo -e ""
}

case "$ACTION" in
    start)
        cat_start
    ;;
    stop)
        cat_stop
    ;;
    restart)
        cat_restart
    ;;
    check)
        ps -ef | grep "zjxw-$SERVER" | grep -v "grep"
    ;;
    log)
        tail -f ${LOG_PATH} -n 500
    ;;
    vim)
        vim ${LOG_PATH}
    ;;
    *)
        usage
    ;;
esac
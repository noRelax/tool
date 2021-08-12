#!/usr/bin/env bash
firstName=$1
secondName=$2
APPS=("task" "schedule" "admin" "api" "gateway" "dbsync" "mlfsync" "external")
if [[ -z "$firstName" ]] || [[ -z "$secondName" ]];then
echo -e  "please execute the command:./install.sh firstName secondName"
fi


if [[ -n "$firstName" ]] && [[ -n "$secondName" ]];then
cd /Users/wusong/zbcm-workspace/$firstName-$secondName && mvn clean package
if [ "$firstName" == "harvest" ];then
for APP in ${APPS[@]};
do
mv /Users/wusong/zbcm-workspace/$firstName-$secondName/$secondName-${APP}/target/$secondName-${APP}.jar /Users/wusong/Downloads/jars/$firstName/
done
fi

if [ "$firstName" != "harvest" ];then
for APP in ${APPS[@]};
do
mv /Users/wusong/zbcm-workspace/$firstName-$secondName/$firstName-${APP}/target/$firstName-${APP}.jar /Users/wusong/Downloads/jars/$firstName/
done
fi
cd /Users/wusong/Downloads/jars/$firstName && zip $firstName.zip *.jar && rm -fr *.jar
fi

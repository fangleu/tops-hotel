#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../../../..")"; pwd)
bin=$tops/script/auto_deploy.py
$bin -t $tops -s /data/tops-hotel-elong-connector/apache-tomcat-7.0.42 -w tops-hotel-elong-connector -p tops-hotel/tops-hotel-selfpay/elong/tops-hotel-elong-connector -r test3 --host 192.168.161.72 -u hotel -a hotel $*

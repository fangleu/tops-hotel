#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/auto_deploy.py
$bin -t $tops -s /opt/purchaser-hotel/apache-tomcat-7.0.37 -w tops-front-purchaser-hotel -p tops-hotel/tops-front-purchaser-hotel -r test --host 192.168.161.69 -u dev -a tarsIch6 -c tops-hotel.settings.gradle $*

#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /opt/app -p tops-hotel/tops-hotel-order-schedule -r test3 --host 192.168.161.81 -u tomcat -a 1 -bin hotelOrderSchedule.sh -c tops-hotel.settings.gradle $lastarg -dz true -dzpn tops-hotel-order-schedule -dzLogPath /data/log/tops/tops-hotel-order-schedule/tops-hotel-order-schedule.log

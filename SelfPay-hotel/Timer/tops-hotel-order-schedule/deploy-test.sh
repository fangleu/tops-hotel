#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/deployer_distzip.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /data/app -p tops-hotel/tops-hotel-order-schedule -r test --host 192.168.164.222 -u tops -a asdf -bin hotelOrderSchedule.sh -c tops-hotel.settings.gradle $lastarg -dz true -dzpn tops-hotel-order-schedule -dzLogPath /data/log/tops/tops-hotel-order-schedule/tops-hotel-order-schedule.log

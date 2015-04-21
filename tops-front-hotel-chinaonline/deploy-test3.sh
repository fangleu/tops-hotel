#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/auto_deploy.py
$bin -t $tops -s /data/tops-front-hotel-chinaonline -w tops-front-hotel-chinaonline -p tops-hotel/tops-front-hotel-chinaonline -r test3 --host 192.168.164.159 -u hotel -a hotel -c tops-hotel.settings.gradle $*

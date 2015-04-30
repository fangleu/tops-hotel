#!/bin/sh

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/auto_deploy.py
$bin -t $tops -s /data/tops-front-purchaser-hotel -w tops-front-purchaser-hotel -p tops-hotel/tops-front-purchaser-hotel -r test3 --host 192.168.161.72  -u hotel -a hotel -c tops-hotel.settings.gradle $*

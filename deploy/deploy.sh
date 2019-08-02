#!/usr/bin/env bash
set -v
cd ../

jar_name=demo_httpclient-0.0.1-SNAPSHOT.jar
#for ip in '10.200.50.96' '10.200.50.97' '10.200.50.95' '10.200.51.118';
for ip in '10.200.50.96';
do
#    ssh root@$ip "mkdir -p /service/apps/client/lib/"
    scp target/$jar_name root@$ip:/service/apps/client/lib/
#    ssh root@$ip "chmod 777 /service/apps/client/lib/$jar_name"
#    ssh root@$ip "ln -s /service/apps/client/lib/$jar_name /etc/init.d/client"

    ssh root@$ip "service client restart"
done

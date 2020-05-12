#!/bin/sh
# if BACKEND_HOST is available as an environment variable, set it in envrironment.prod.ts and nginx.conf
if [ -z ${BACKEND_HOST+x} ] && [ -z ${FRONTEND_HOST+x} ];
    then
        echo "BACKEND_HOST or FRONTEND_HOST is unset, going with default";
    else
        echo "setting BASE_URL in environment.prod.ts to http://$FRONTEND_HOST";
        sed  -i "s/BASE_URL: '.*'/BASE_URL: '$BACKEND_HOST'/g" src/environments/environment.prod.ts
        echo "setting upstream backend in nginx.conf to $BACKEND_HOST";
        sed -i  "s/upstream backend {.*}/upstream backend { server $BACKEND_HOST:8080; }/g" nginx.conf
        echo "setting FE server name in nginx.conf to http://$FRONTEND_HOST";
        sed -i  "s/server_name .*;/server_name http://$FRONTEND_HOST;/g" nginx.conf
fi

ng build --prod
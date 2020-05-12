#!/bin/sh
# if BACKEND_HOST is available as an environment variable, set it in envrironment.prod.ts and nginx.conf
if [ -z ${BACKEND_HOST+x} ] && [ -z ${FRONTEND_HOST+x} ];
    then
        echo "BACKEND_HOST or FRONTEND_HOST is unset, going with default";
    else
        echo "setting BASE_URL in environment.prod.ts to $FRONTEND_HOST";
        sed  -i "s/frontend/$FRONTEND_HOST/g" src/environments/environment.prod.ts
        echo "setting proxy_pass in nginx.conf to $BACKEND_HOST";
        sed -i  "s/backend/$BACKEND_HOST/g" nginx.conf
        echo "setting FE server name in nginx.conf to $FRONTEND_HOST";
        sed -i  "s/frontend/$FRONTEND_HOST/g" nginx.conf
fi

ng build --prod
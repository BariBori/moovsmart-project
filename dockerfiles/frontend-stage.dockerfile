FROM node:current-alpine
RUN apk add --no-cache chromium
RUN apk add --no-cache --virtual .build-deps alpine-sdk python 
COPY moovsmart/package.json moovsmart/package-lock.json /builds/fort.zsuzsanna/angular-moovsmart/
WORKDIR /builds/fort.zsuzsanna/angular-moovsmart
RUN npm ci --silent && apk del .build-deps

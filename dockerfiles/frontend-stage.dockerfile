FROM node:current-alpine
RUN apk add --no-cache chromium
RUN apk add --no-cache --virtual .build-deps alpine-sdk python 
COPY moovsmart/package.json moovsmart/package-lock.json /frontend/
WORKDIR /frontend
RUN npm ci --silent && apk del .build-deps
RUN pwd
RUN ls

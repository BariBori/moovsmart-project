FROM node:current-alpine
RUN apk add --no-cache chromium
ENV CHROME_BIN /usr/bin/chromium-browser
RUN apk add --no-cache --virtual .build-deps alpine-sdk python 
COPY moovsmart/package-lock.json moovsmart/package.json /frontend/
WORKDIR /frontend
RUN npm ci --silent
RUN apk del .build-deps

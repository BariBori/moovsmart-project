FROM node:current-alpine
RUN apk add --no-cache chromium
ENV CHROME_BIN /usr/bin/chromium-browser
RUN apk add --no-cache --virtual .build-deps alpine-sdk python 
COPY moovsmart/package-lock.json moovsmart/package.json /stage/
WORKDIR /stage
RUN npm ci --silent
RUN apk del .build-deps

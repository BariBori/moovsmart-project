FROM node:current-alpine
RUN apk add --no-cache chromium
ENV CHROME_BIN /usr/bin/chromium-browser
RUN apk add --no-cache --virtual .build-deps alpine-sdk python 
COPY package-lock.json package.json /frontend/
WORKDIR /frontend
RUN npm ci --silent
RUN apk del .build-deps

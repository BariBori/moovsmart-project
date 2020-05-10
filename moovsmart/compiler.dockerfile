FROM node:current-alpine as base
RUN apk add --no-cache --virtual .build-deps make gcc g++ python 
COPY package-lock.json package.json /frontend/
WORKDIR /frontend
RUN npm ci --silent
RUN apk del .build-deps
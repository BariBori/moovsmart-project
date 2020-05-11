FROM node:current-alpine
RUN pwd
# RUN apk add --no-cache --virtual .build-deps make gcc g++ python 
COPY package-lock.json package.json /frontend/
# RUN npm ci --silent
# RUN apk del .build-deps
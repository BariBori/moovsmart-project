FROM node:current-alpine
RUN apk add --no-cache chromium
ENV CHROME_BIN /usr/bin/chromium-browser
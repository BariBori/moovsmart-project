FROM gitlab.progmasters.hu:4567/fort.zsuzsanna/angular-moovsmart/frontend-compile:latest
RUN apk add --no-cache chromium
ENV CHROME_BIN /usr/bin/chromium-browser
RUN pwd
# COPY angular.json tsconfig.json tsconfig.app.json ci-build.sh /
# RUN npm run-script ci-build
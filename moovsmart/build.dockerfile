FROM gitlab.progmasters.hu:4567/fort.zsuzsanna/angular-moovsmart/frontend-compiler AS chromium
# RUN apk add --no-cache chromium
# ENV CHROME_BIN /usr/bin/chromium-browser
# FROM chromium
COPY src /frontend/src/
COPY angular.json tsconfig.app.json ci-build.sh /frontend/
RUN npm run-script ci-build
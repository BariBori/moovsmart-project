FROM gitlab.progmasters.hu:4567/fort.zsuzsanna/angular-moovsmart/frontend-compiler
RUN apk add --no-cache chromium
ENV CHROME_BIN /usr/bin/chromium-browser
COPY moovsmart/src /frontend/src/
RUN npm run-script ci-build
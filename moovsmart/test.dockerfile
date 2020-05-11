
FROM gitlab.progmasters.hu:4567/fort.zsuzsanna/angular-moovsmart/frontend-compile
RUN apk add --no-cache chromium
ENV CHROME_BIN /usr/bin/chromium-browser
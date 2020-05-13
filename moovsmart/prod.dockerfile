FROM nginx:latest
COPY dist/moovsmart /srv/moovsmart
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
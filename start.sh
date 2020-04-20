#!/bin/sh
java -Dspring.profiles.active=development -Xmx1024m -jar /home/ubuntu/project.jar > app.log 2>&1 &

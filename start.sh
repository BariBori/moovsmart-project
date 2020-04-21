#!/bin/sh
java -Dspring.profiles.active=development -Xmx1024m -jar /home/ec2-user/project.jar > app.log 2>&1 &

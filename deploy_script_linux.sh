#!/bin/sh
#INIT PROJECTS RELATED INFO
#------------- UPDATE ALWAYS CORRESPONDINGLY!
# pom.xml Artifact id must be 'project'
remote_address=34.254.251.78
frontend_source_location=./moovsmart/dist/moovsmart/*
backend_source_location=./target/project-1.0-SNAPSHOT.jar
frontend_remote_location=/home/ubuntu/frontend/
backend_remote_location=/home/ubuntu/
pem_file_full_path=2019NovGroup.pem

#BUILD PROJECT
cd moovsmart
npm run build
cd ..
mvn clean package -Dskiptest=true
sudo chmod 400 $pem_file_full_path

#COPY LOCAL FILES TO SERVER
scp -i $pem_file_full_path -f $frontend_source_location ubuntu@$remote_address:$frontend_remote_location
scp -i $pem_file_full_path $backend_source_location ubuntu@$remote_address:$frontend_remote_location/project.jar.new

#UPDATE .JAR WITH NEW, AND RESTART
ssh -i $pem_file_full_path ubuntu@$remote_address './shutdown.sh; mv project.jar.new project.jar; ./start.sh'

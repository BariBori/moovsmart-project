# #!/bin/sh
set -x
set -e
set -v
# #INIT PROJECTS RELATED INFO
# #------------- UPDATE ALWAYS CORRESPONDINGLY!
# pom.xml Artifact id must be 'project'
remote_address=moovsmart-demo.progmasters.hu
remote_address=3.18.213.29
frontend_folder_name=moovsmart
frontend_source_location=./$frontend_folder_name/dist/$frontend_folder_name/*

backend_source_location=./target/project-1.0-SNAPSHOT.jar
frontend_remote_location=/home/ec2-user/frontend
backend_remote_location=/home/ec2-user
pem_file_full_path=moovsmart.pem

# #BUILD PROJECT
cd $frontend_folder_name
sudo apt-get install npm
npm link @angular/cli
ng build --prod
cd ..
mvn clean package -DskipTests=true
chmod 400 $pem_file_full_path

#COPY LOCAL FILES TO SERVER
scp  -i $pem_file_full_path $frontend_source_location ec2-user@$remote_address:$frontend_remote_location
scp  -i $pem_file_full_path $backend_source_location ec2-user@$remote_address:$backend_remote_location/project-1.0-SNAPSHOT.jar.new

#UPDATE .JAR WITH NEW, AND RESTART
ssh -i $pem_file_full_path ec2-user@$remote_address './shutdown.sh; mv project-1.0-SNAPSHOT.jar.new project.jar; ./start.sh'

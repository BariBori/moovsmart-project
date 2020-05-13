
#!/bin/sh
scp docker-compose.yml $server_user@$remote_address:/home/ec2-user/docker-compose.yml
docker login -u $CI_REGISTRY_USER -p $CI_REGISTRY_PASSWORD $CI_REGISTRY
docker pull $MOOVSMART_REGISTRY/backend-prod:latest  
docker pull $MOOVSMART_REGISTRY/frontend-prod:latest 
ssh $server_user@$remote_address 'docker-compose down && docker system prune -f && docker-compose up'
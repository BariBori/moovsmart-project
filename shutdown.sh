#!/bin/sh
search_terms='project.jar'
kill $(ps aux |grep "$search_terms" | grep -v 'grep' | awk '{print $2}')1>/dev/null 2>&1 || true

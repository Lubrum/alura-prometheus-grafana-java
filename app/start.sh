#!/bin/bash

/usr/lib/jvm/java-20-zulu-amd64/bin/java -jar -Xms128M -Xmx128M -XX:PermSize=64m -XX:MaxPermSize=128m -Dspring.profiles.active=prod target/forum.jar
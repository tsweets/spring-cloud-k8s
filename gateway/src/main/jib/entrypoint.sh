#!/bin/sh

echo "Starting Spring API Gateway Server"
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "org.beer30.springcloud.gateway.SpringGatewayApplication"  "$@"

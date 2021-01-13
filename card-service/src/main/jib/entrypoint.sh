#!/bin/sh

echo "Starting Atlas Server"
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "net.pbi.atlas.atlasserver.AtlasServerApplication"  "$@"
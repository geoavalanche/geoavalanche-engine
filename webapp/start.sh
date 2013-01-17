# NOTA: con questi parametri non va in PermGen Space
export MAVEN_OPTS="-DGEOSERVER_DATA_DIR=./DATA -Dmaven.test.skip=true -Xms512m -Xmx2048m -XX:PermSize=64m -XX:MaxPermSize=1024m" &&  mvn -o jetty:run-exploded


# TODO: -DGEOSERVER_DATA_DIR=./DATA
# VEDI: http://docs.geoserver.org/latest/en/user/datadirectory/index.html
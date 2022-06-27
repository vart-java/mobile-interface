#!/bin/bash

mkdir /opt/mongodb
echo directory created

cd /opt/mongodb || exit
echo directory changed

echo 'db.auth("madmin", "msecret")
db = db.getSiblingDB("mobileinterface")

db.createUser({
  user: "mobile",
  pwd: "qwerty123",
  roles: [
    {
      role: "readWrite",
      db: "mobileinterface",
    },
  ],
});' > mongo-init.js
echo mongo-init.js created

docker run -p 27017:27017 -d --name mongo_db -e MONGO_INITDB_ROOT_USERNAME=madmin -e MONGO_INITDB_ROOT_PASSWORD=msecret -e MONGO_INITDB_ROOT_DATABASE=admin -v /opt/mongodb/mongo-init.js:/docker-entrypoint-initdb.d/mongo-init.js:ro mongo
echo docker container run

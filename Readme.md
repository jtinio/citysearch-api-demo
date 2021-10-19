### Introduction
***
This is a simple web app that search a city by prefix(LIKE string%), suffix(LIKE %string) or combination(LIKE %string%)

### Cloning Repository
***
```
git clone https://github.com/jtinio/citysearch-api-demo.git
```

### Running application using docker
***
##### 1) Running docker compose in daemon mode: add --build flag if you want to force rebuild
```
docker-compose up -d
```
##### 2)  Testing api endpoint using curl command
```
   * prefix(LIKE string%) - curl -v 'http://localhost:8080/api/city/search?flag=PREFIX&name=Los'
   * contains(LIKE %string%) - curl -v 'http://localhost:8080/api/city/search?flag=CONTAINS&name=Yo'
   * suffix(LIKE %string) - curl -v 'http://localhost:8080/api/city/search?flag=SUFFIX&name=Vegas'
```
##### 3) Accessing website 
```
http://localhost
```
##### 4) Accessing Swagger UI
```
http://localhost:8080/api/swagger-ui/index.html
```
##### 5) Stopping docker compose
```
docker-compose down
```

### Testing the backend
***
##### 1) set permission to grant only the owner of that file execution:
```
chmod u+x ./scripts/backend-test-script.sh
```
##### 2) run the script:
```
./scripts/backend-test-script.sh
```
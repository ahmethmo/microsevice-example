# microsevice-example

## rest-book
```sh
cd .\rest-book\
```

For locally
```sh
mvn quarkus:dev
```

For Docker Image 
```sh
mvn package -DskipTests=true 
```

## rest-number
```sh
cd .\rest-number\
```

For locally
```sh
mvn quarkus:dev
```

For Docker Image 
```sh
mvn package -DskipTests=true 
```

## Docker container
```sh
docker-compose -f docker-compose.yaml up
```

## Servers
| URL | Methods | Parameters |
| ------ | ------ | ------ |
| localhost:8701/api/numbers | GET | - |
| localhost:8702/api/books | POST | { "title": "t", "author": "a", "year_of_publication": 1988, "genre": "g" } |

#Development environment

    Windows 10 Home 64-bit build 1903
    git version 2.16.1.windows.1
    docker-compose version 1.24.1, build 4667896b
    jdk1.8.0_131
    IntelliJ IDEA 2020.1.2 (Ultimate Edition)

#Project build and run

To run application do the following steps:

1.Clone Git repository
https://github.com/AlexanderTkatchev/libertex

2. Enter cloned repository folder and find file gradlew.bat

3. To build project execute the command:
    gradlew build --refresh-dependencies 

4. To run project execute the command:
    gradle runBoot
    
5. To create Docker container execute the following command:
    docker-compose build    

6. To run Docker container execute the following command:
    docker-compose run
    
#Swagger UI    
 
All REST API functions can be executed using Swagger UI in a browser:

    http://localhost:8080/swagger-ui.html


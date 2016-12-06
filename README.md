![Build status](https://circleci.com/gh/sogilis/sonarqube-for-android-example.png?circle-token= 4a0404fab8d9699fb85f4286b621a88b980159a5)

![Build status](https://circleci.com/gh/sogilis/sonarqube-for-android-example.png?circle-token= 4a0404fab8d9699fb85f4286b621a88b980159a5)

# Sample application

This application demonstrate how to configure Sonarqube with gradle for an Android project.
Sources are based on a Google sample app.

# How to analyze this project
- Install [Docker](https://www.docker.com)

- Install and run [Sonqube Docker image](https://store.docker.com/images/3f8fc4ce-eb8e-40ad-88ba-69e97299c64f?tab=description) `sonarqube:alpine`:
  ```
  docker run -d -p 9000:9000 -p 9092:9092 sonarqube:alpine
  ```

`sonarqube:alpine` is a lightweight version (5M) or sonarqube:latest. See [docker store page](https://store.docker.com/images/3f8fc4ce-eb8e-40ad-88ba-69e97299c64f?tab=description) for details.

- Run analysis
```
./gradlew clean test jacocoTestReport sonarqube \
          -Dsonar.host.url=http://[Sonarqube container IP]:9000 \
          --info --stacktrace
```
Replace `[Sonarqube container IP]` with corresponding IP address.

- Watch result here: http://[Sonarqube container IP]:9000
  * Log in with default user: `admin/admin`
  * **Warning**: analyses is done by a background task and can take a view seconds. You can follow background task here: `Administration > Projects > Background Tasks`

- See Sonarqube logs:
```
docker logs sonarqube:alpine
```

# 1. 베이스 이미지 (Amazon Corretto 21 사용)
FROM amazoncorretto:21

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle로 빌드된 JAR 파일을 이미지 내부로 복사
# (build/libs 폴더 밑에 .jar 파일이 있다고 가정)
COPY build/libs/project_DB_React-0.0.1-SNAPSHOT.jar app.jar

# 4. 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
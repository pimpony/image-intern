# Spring Images

## First Step เขียน Dockerfile ซึ้งทำแบบ multi-stage เพื่อลดขนาดของ image 
```
##  build stage 
// FROM คือ ตัว Run app ซึ้ง Project คือ spring framework 
	FROM maven:3.6.1-11-slim AS build-stage 
// เป็นการ copy src  ไปไว้ที่ /workspace/src
	COPY src /workspace/src
// เป็นการ copy pom.xml ไปไว้ที่  /workspace
	COPY pom.xml /workspace
// WORKDIR เพื่อช่วยในตอนเปิดเข้าจะไปที่ /workspace จาก default ที่ควรอยู่ root //หากยังไม่มี folder จะสร้าง folder ขึ้นมา และเปิดเข้าที่ folder
	WORKDIR /workspace
// เป็นคำสั่งของการให้ไปโหลด dependency
	RUN mvn clean install
image 
# production stage
//  เป็น Java ซึ้งเลือกใช้ Java 11
	FROM openjdk:11.0-slim
//  เป็นการระบุ Port 
	EXPOSE 5000
// Copy เฉพาะตัว Production Build จาก build-stage ไปไว้ใน Folder /workspace/target/*.jar app.jar
	COPY --from=build-stage  /workspace/target/*.jar app.jar
// เป็นขั้นตอน Command คำสั่ง 
	ENTRYPOINT ["java","-jar","app.jar"]
```

##ขั้นตอนการทำ images
```
// build image
	docker build -t xxpxiixn/springimages .
```

##ขั้นตอนการ Push image ขึ้น
```
// Push  a Docker container Image
	docker push xxpxiixn/springimages
```
##Link image
```
https://hub.docker.com/r/xxpxiixn/springimages
```




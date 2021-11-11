# Js Image

## First Step เขียน Dockerfile 
```
##  build stage 
// FROM คือ ตัว Run app ซึ้ง Project คือ vue.js framework based on nodejs จึงต้อง  pull node และเลือกใช้เป็น Version latest และเป็น  Alpine เพื่อตอบโจทย์การใช้ Container ซึ้งจะมีขนาดที่เล็ก
	FROM node:lts-alpine as build-stage
// WORKDIR เพื่อช่วยในตอนเปิดเข้าจะไปที่ /app จาก default ที่ควรอยู่ root //หากยังไม่มี folder จะสร้าง folder ขึ้นมา และเปิดเข้าที่ folder
	WORKDIR /app
// เป็นการ copy package ที่ใช้งาน 
	COPY package*.json ./
// เป็นคำสั่งของ node ในการ install
	RUN npm install
// copy จากเครื่องเรา ไปสู่บน Container
	COPY . .
// เป็นคำสั่งของ node ในการ build
	RUN npm run build
image 
# production stage
// ใช้ในการทำ web server ในการเปิด website มิฉะนั้นจะไม่สามารถเปิดเข้าเว็บตรงๆได้
	FROM nginx:stable-alpine as production-stage
// Copy เฉพาะตัว Production Build จาก build-stage ไปไว้ใน Folder /usr/share/nginx/html
	COPY --from=build-stage /app/dist /usr/share/nginx/html
// เป็นการระบุ Port แต่เนื่อง nginx default ที่ 80 หากจะเปลี่ยนจะต้องทำ default.conf เพื่อระบุ แต่เนื่องโปรเจ็คตอนนี้  ไม่ได้ทำ File ดังกล่าว จึงต้องเข้าผ่าน 80 
	EXPOSE 8080 
// เป็นขั้นตอน Command คำสั่ง 
	CMD ["nginx", "-g", "daemon off;"]

```

## ขั้นตอนการทำ image
```
// build image
	docker build -t xxpxiixn/jsimages
```

## ขั้นตอนการ Push image ขึ้น
```
// Push  a Docker container Image
	docker push xxpxiixn/jsimages 
```

## Link image
```
https://hub.docker.com/r/xxpxiixn/jsimages
```


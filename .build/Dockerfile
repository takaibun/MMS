FROM maven:3.8-openjdk-17 as service-build-stage

WORKDIR /opt/pmm/src
#复制源代码
COPY service .
#构建Java项目
RUN mvn clean package


FROM node:lts AS console-build-stage

WORKDIR /opt/pmm/console

COPY console .

RUN npm install &&  \
    npm run build

FROM openjdk:17-jdk-slim AS final-stage

COPY --from=console-build-stage /opt/pmm/console/dist/ /opt/pmm/console/
COPY --from=service-build-stage /opt/pmm/src/target/*.jar /opt/pmm/service/pmm.jar

RUN apt-get update && apt-get install -y nginx

COPY bin/run.sh /opt/pmm/bin/run.sh
COPY .build/nginx.conf /etc/nginx/nginx.conf

VOLUME /config

CMD ["/bin/bash","/opt/pmm/bin/run.sh"]
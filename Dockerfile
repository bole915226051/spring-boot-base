# 使用 OpenJDK 镜像作为基础镜像
FROM openjdk:8-jdk-alpine

# 将本地的 JAR 文件复制到镜像中
COPY target/base-0.0.1-SNAPSHOT.jar /app/

# 设置工作目录
WORKDIR /app

# 定义容器启动时运行的命令
CMD ["java", "-jar", "base-0.0.1-SNAPSHOT.jar"]
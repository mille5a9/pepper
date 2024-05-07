FROM openjdk:19-jdk
EXPOSE 8080:8080
RUN mkdir /bot
COPY ./build/libs/*-all.jar /bot/bot.jar
ENTRYPOINT ["java","-jar","/bot/bot.jar"]
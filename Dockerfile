FROM centos

COPY . /workspace

WORKDIR /workspace

RUN yum install -y maven

WORKDIR /workspace/build/libs

ENTRYPOINT ["java", "-jar", "OnlineEducation-0.0.1-SNAPSHOT.jar"]

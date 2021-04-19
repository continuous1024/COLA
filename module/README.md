# 模块应用

## 数据库应用

mvn archetype:generate  \
-DgroupId=com.huan \
-DartifactId=database-module \
-Dversion=1.0.0-SNAPSHOT \
-Dpackage=com.huan \
-DarchetypeArtifactId=cola-framework-archetype-web \
-DarchetypeGroupId=com.alibaba.cola \
-DarchetypeVersion=4.0.1

## 内容

1. 死锁学习
2. AOP学习

## 分布式应用

### 生成命令

mvn archetype:generate  \
-DgroupId=com.huan.distributed \
-DartifactId=distributed-module \
-Dversion=1.0.0-SNAPSHOT \
-Dpackage=com.huan.distributed \
-DarchetypeArtifactId=cola-framework-archetype-web \
-DarchetypeGroupId=com.alibaba.cola \
-DarchetypeVersion=4.0.1

## 内容

1. 雪花算法生成分布式连续ID

## 视频流应用

### 生成命令

mvn archetype:generate  \
-DgroupId=com.huan.stream \
-DartifactId=stream-module \
-Dversion=1.0.0-SNAPSHOT \
-Dpackage=com.huan.stream \
-DarchetypeArtifactId=cola-framework-archetype-web \
-DarchetypeGroupId=com.alibaba.cola \
-DarchetypeVersion=4.0.1


mvn archetype:generate  \
-DgroupId=com.huan.stream.service \
-DartifactId=stream-service \
-Dversion=1.0.0-SNAPSHOT \
-Dpackage=com.huan.stream.service \
-DarchetypeArtifactId=cola-framework-archetype-service \
-DarchetypeGroupId=com.alibaba.cola \
-DarchetypeVersion=4.0.1


mvn archetype:generate  \
-DgroupId=com.huan.bigdata.service \
-DartifactId=bigdata-service \
-Dversion=1.0.0-SNAPSHOT \
-Dpackage=com.huan.bigdata \
-DarchetypeArtifactId=cola-framework-archetype-service \
-DarchetypeGroupId=com.alibaba.cola \
-DarchetypeVersion=4.0.1

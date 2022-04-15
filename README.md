# Reshape on Apache Flink
This is the code for the implementation of Reshape on Apache Flink 1.13. More details about Flink and how to build it can be found at [Flink's github](https://github.com/apache/flink).

## Building the project
```console
git clone https://github.com/reshapeskew/reshape-on-flink.git
cd reshape-on-flink
mvn clean package -Drat.numUnapprovedLicenses=1000 -DskipTests -Dfast -Dcheckstyle.skip
```
## Running the project:

This step is the same as Flink's original instruction. However, since Reshape is already a built-in functionality of Flink in this repo, you can pass `-DenableReShape=true` to flink-conf.yaml to enable reshape when starting Flink.

## Resahpe parameters:

Some parameters of reshape can be tuned:
| Parameter name  | Type | Default value | Usage |
| ------------- | ------------- |  ------------- |  ------------- |  
| enableReShape  | Boolean      |  false  | enable/disable reshape | 
| hiThreshold  | Double  |  0.8  | worker with busy ratio higher than this threshold will be treated as skewed | 
| loThreshold  | Double  |  0.2  | worker with busy ratio lower than this threshold will be treated as free | 
| reshapeFreq  | Long      |  10000  | interval of triggering one reshape iteration (unit:ms) | 


## Modified/Added Files:

flink-runtime/src/main/java/org/apache/flink/runtime/dispatcher/Dispatcher.java
flink-runtime/src/main/java/org/apache/flink/runtime/executiongraph/DefaultExecutionGraph.java
flink-runtime/src/main/java/org/apache/flink/runtime/executiongraph/Execution.java
flink-runtime/src/main/java/org/apache/flink/runtime/executiongraph/ExecutionGraph.java
flink-runtime/src/main/java/org/apache/flink/runtime/executiongraph/ExecutionJobVertex.java
flink-runtime/src/main/java/org/apache/flink/runtime/executiongraph/ExecutionVertex.java
flink-runtime/src/main/java/org/apache/flink/runtime/io/network/api/writer/ChannelSelectorRecordWriter.java
flink-runtime/src/main/java/org/apache/flink/runtime/io/network/api/writer/RecordWriterBuilder.java
flink-runtime/src/main/java/org/apache/flink/runtime/jobgraph/tasks/AbstractInvokable.java
flink-runtime/src/main/java/org/apache/flink/runtime/jobmanager/slots/TaskManagerGateway.java
flink-runtime/src/main/java/org/apache/flink/runtime/jobmaster/JobManagerRunner.java
flink-runtime/src/main/java/org/apache/flink/runtime/jobmaster/JobMaster.java
flink-runtime/src/main/java/org/apache/flink/runtime/jobmaster/JobMasterGateway.java
flink-runtime/src/main/java/org/apache/flink/runtime/jobmaster/JobMasterServiceLeadershipRunner.java
flink-runtime/src/main/java/org/apache/flink/runtime/jobmaster/RpcTaskManagerGateway.java
flink-runtime/src/main/java/org/apache/flink/runtime/rest/handler/job/JobVertexBackPressureHandler.java
flink-runtime/src/main/java/org/apache/flink/runtime/scheduler/SchedulerBase.java
flink-runtime/src/main/java/org/apache/flink/runtime/scheduler/SchedulerNG.java
flink-runtime/src/main/java/org/apache/flink/runtime/scheduler/adaptive/AdaptiveScheduler.java
flink-runtime/src/main/java/org/apache/flink/runtime/taskexecutor/TaskExecutor.java
flink-runtime/src/main/java/org/apache/flink/runtime/taskexecutor/TaskExecutorGateway.java
flink-runtime/src/main/java/org/apache/flink/runtime/taskexecutor/TaskExecutorGatewayDecoratorBase.java
flink-runtime/src/main/java/org/apache/flink/runtime/taskmanager/Task.java
flink-runtime/src/main/java/org/apache/flink/runtime/webmonitor/RestfulGateway.java
flink-runtime/src/main/scala/org/apache/flink/runtime/reshape/ControllerSimulator.scala
flink-runtime/src/main/scala/org/apache/flink/runtime/reshape/WorkerSimulator.scala
flink-runtime/src/test/java/org/apache/flink/runtime/executiongraph/utils/SimpleAckingTaskManagerGateway.java
flink-runtime/src/test/java/org/apache/flink/runtime/jobmaster/TestingJobManagerRunner.java
flink-runtime/src/test/java/org/apache/flink/runtime/jobmaster/utils/TestingJobMasterGateway.java
flink-runtime/src/test/java/org/apache/flink/runtime/scheduler/TestingSchedulerNG.java
flink-runtime/src/test/java/org/apache/flink/runtime/scheduler/adaptive/StateTrackingMockExecutionGraph.java
flink-runtime/src/test/java/org/apache/flink/runtime/taskexecutor/TestingTaskExecutorGateway.java
flink-runtime/src/test/java/org/apache/flink/runtime/webmonitor/TestingRestfulGateway.java
flink-streaming-java/pom.xml
flink-streaming-java/src/main/java/org/apache/flink/streaming/runtime/partitioner/KeyGroupStreamPartitioner.java
flink-streaming-java/src/main/java/org/apache/flink/streaming/runtime/tasks/StreamTask.java

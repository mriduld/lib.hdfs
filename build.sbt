organization := "com.md"

name := "lib-hdfs"

version := "0.1.0"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq (
	 "dev.zio" 				%% "zio" 							   % "1.0.0-RC17",
	 "org.apache.hadoop"     %  "hadoop-common"                    % "2.6.0-cdh5.13.3"   % Provided
)

scalacOptions ++= Seq("-deprecation", "-feature")
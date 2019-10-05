@echo off
set currentDir=%cd%
echo Current directory : %currentDir%

set workingDir=%userprofile%\data-analytics
echo Wroking dirctory : %workingDir%
mkdir %workingDir%
cd /d %workingDir%
echo Downloading apache cassandra
curl http://mirrors.estointernet.in/apache/cassandra/3.11.4/apache-cassandra-3.11.4-bin.tar.gz -o cassandra.tar.gz

echo Extracting to %workingDir%
tar -x -f cassandra.tar.gz

echo Starting cassandra, wait for 60 sec..
start apache-cassandra-3.11.4\bin\cassandra.bat
timeout /t 60 > nul

cd /d %currentDir%
set schemaFilePath= ../../../../../persistence/src/main/resources/cassandra/schema/
set schemaFileName=entities.cql

cd /d %schemaFilePath%
set cqlCmd=cqlsh -e "SOURCE '%cd%/%schemaFileName%'"
echo importing schema using : %cqlCmd%
%cqlCmd%
cd /d %currentDir%

cd ../../../

gradle clean build -x test
gradle run
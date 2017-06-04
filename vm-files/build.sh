#!/bin/bash

wget https://github.com/anand-gangadharan-infosys/shopquiz/archive/master.zip
unzip master.zip
cd shopquiz-master/quick-simulate
mvn install
cp -r target/quick-simulate-1.0.jar /root
cp -r target/dependency-jars /root
cp ../quick-simulate/SampleRun.txt /root/

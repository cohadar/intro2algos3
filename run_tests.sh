#!/bin/sh

# compile all java files
javac -target 1.8 -source 1.8 -cp '.:../lib/*' -Xlint:unchecked -Xlint:deprecation *.java

# run all test files
for file in *Test.java; do
	echo ================================================
	echo $file
	echo ------------------------------------------------
    java -Xss512m -ea -cp '.:../lib/*' org.junit.runner.JUnitCore ${file%.*}
done

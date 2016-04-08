#!/bin/sh

# compile all java files
javac -target 1.8 -source 1.8 -cp '.:../lib/*' -Xlint:unchecked -Xlint:deprecation *.java

# collect test class names
classList=""
for file in *Test.java; do
    baseName=${file%.java}
    echo $baseName
    classList="$classList $baseName"
done
echo '-------------------------'

# run all tests
java -Xss512m -ea -cp '.:../lib/*' org.junit.runner.JUnitCore $classList

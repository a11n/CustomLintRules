#!/bin/sh

# Build .jar
./gradlew clean assemble

# Install
if [ ! -d "~/.android/lint/" ]; then
  mkdir ~/.android/lint/
fi

cp build/libs/* ~/.android/lint/
@echo off

set JVM_PATH="D:\Yeni_Kilasor\Kurulmus_Dosyalar\Programlar\Java\jdk-11.0.12\bin"
set JVM_PATH2="D:\Yeni_Kilasor\Kurulmus_Dosyalar\Programlar\Java\openjdk-11.0.9.1\bin"

set TROVE_LIB=../lib/trove4j-3.0.3.jar;

set CLASS_LIB=%TROVE_LIB%
set CLASS_PATH=-cp ../bin;%CLASS_LIB%

REM %JVM_PATH%\java.exe %CLASS_PATH% com.emrecellebi.Console %*
REM %JVM_PATH2%\java.exe %CLASS_PATH% com.emrecellebi.Console %*
java.exe %CLASS_PATH% com.emrecellebi.Console %*
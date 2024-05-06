@echo off

echo Running Main from bin...
cd bin
java Main
goto end

:error
echo Compilation failed, please check your code.

:end
pause

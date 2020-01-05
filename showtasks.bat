call runcrud.bat
if "%ERRORLEVEL%" == "0" goto StartAppInChromeBrowser
echo App running caused some error
goto ErrorHandler

:StartAppInChromeBrowser
set url="http://localhost:8080/crud/v1/task/getTasks"
start chrome %url%
goto End

:ErrorHandler
echo.
echo Work finished with errors
exit

:End
echo.
echo Work is finished
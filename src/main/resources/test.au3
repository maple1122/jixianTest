WinWait("打开","","5")
ControlFocus("打开","","Edit1")
Sleep(100)
ControlSetText("打开","","Edit1","D:\autotest\jixianAutoTest\src\main\resources\imageTest.jpg")
Sleep(100)
ControlClick("打开","","Button1")

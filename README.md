## Powershell Flow
Not including Trusted Cert Creation (TestCert)
1. `cd “C:\Users\Robert Ohly\Documents\Algonquin\CST8412-UI-App-Dev\lap9exe”`
2. `$sourceFiles = Get-ChildItem -Recurse -Filter "*.java" -File -Path "src\main\java" | Select-Object -ExpandProperty FullName`
3. `mkdir target\mods\com.example.lab9exe\`
4. `Get-ChildItem src\main\resources\com\example\lab9exe\ -Include *.fxml, *.png -File -Recurse | Copy-Item -Destination target\mods\com\example\lab9exe\`
5. `jlink --module-path "C:\Program Files\Java\javafx-jmods-25.0.1;target\mods" --add-modules com.example.lab9exe,javafx.controls,javafx.fxml,javafx.graphics,javafx.base --output target\image`
6. `jpackage --type exe -d target\installer -n "Lab 9" --module com.example.lab9exe/com.example.lab9exe.Lab9Launcher --runtime-image target\image --icon icon.ico --vendor "Robert Ohly" --win-console`
7. `cmd`
8. `"C:\Program Files (x86)\Windows Kits\10\bin\10.0.26100.0\x64\signtool.exe" sign /s My /n "TestCert" /fd SHA256 /tr http://timestamp.digicert.com /td SHA256 "Lab 9-1.0.exe"`
9. `C:\Users\Robert Ohly\Documents\Algonquin\CST8412-UI-App-Dev\lap9exe>"C:\Program Files (x86)\Windows Kits\10\bin\10.0.26100.0\x64\signtool.exe" verify /pa /v "target/installer/Lab 9-1.0.exe"`
10. `"target/installer/Lab 9-1.0.exe"`

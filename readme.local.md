


# Deployment

```powershell
$jboss_home='C:\devel\projects\demo>c:\devel\opt\wildfly-14.0.0.Final'
&$jboss_home\bin\jboss-cli.bat --user=admin --password=admin1234 `
    --connect --controller=localhost:9990 `
    --command="deploy target\rundeck-delegate.war --force --name=rundeck-delegate --runtime-name=rundeck-delegate"
```


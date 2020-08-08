def call(String yanzheng = '',String servicename = '',String batchDeployServer = '',String BackupShellName = '',String DeployShellName = '',String warPath = '',String WarName='',int timeSize=1) {
//一个流水线里只能有一个声明式的 
    node("master") {
        environment {
        }
        stage(servicename+'备份'){
            backupTheWar_common(yanzheng,servicename,batchDeployServer,BackupShellName)			 
        }
        stage(servicename+'发布'){			
            deployTheWar_common(yanzheng,servicename,batchDeployServer,DeployShellName,warPath,WarName)
            sleep 10		
        }               
        stage(servicename+'健康检查'){
            checkService_common(yanzheng,batchDeployServer,timeSize)
        }
    }
}

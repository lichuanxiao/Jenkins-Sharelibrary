def call(String yanzheng='',String servicename='',String batchDeployServer='',String BackupShellName=''){ 
    def bsn=BackupShellName
        def serverlistTemp=getDeployServer(yanzheng,batchDeployServer)
        //备份
        for(String values: serverlistTemp){
            list1=values.split(',') 
            print("backupTheWar :  "+list1[0]+" "+list1[1]+" "+list1[2])						  
            listServerIP=list1[0]
            listServerPort=list1[1]
            listServerUser=list1[2]
            echo "${WORKSPACE}"
            echo "${workspace}"
            sh "ansible  ${listServerIP}  -u ${listServerUser} -m copy -a 'src=${WORKSPACE}/${env.servicename}/JenkinsCICD/jenkins dest=/home/${listServerUser} mode=0755'"
            sh "ansible  ${listServerIP}  -u ${listServerUser} -m shell -a  'sh ${bsn}'"
        }
        return " ${batchDeployServer} backup is comeplete"	        
}

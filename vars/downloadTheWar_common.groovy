def call(String servicename='',String serviceNameBatchDeployServer='',String patternName='',String warName='',String download_path=''){ 
    rtServer(
        id: "ARTIFACTORY_SERVER",
        url: "http://172.16.136.13:8081/artifactory",
        credentialsId: "artifactory-admin",
    )
    rtDownload(
        serverId: "ARTIFACTORY_SERVER",
        spec:"""{"files":[{"pattern":"${patternName}","target":"${warName}"}]}"""
    )	
    if(serviceNameBatchDeployServer.length() != 0 ){
        def serverlist=serviceNameBatchDeployServer.split('\\|')
            for(values in serverlist){
                //values=serverlist[0]
                list1=values.split(',') 
                print(list1[0]+" "+list1[1]+" "+list1[2])						  
                listServerIP=list1[0]
                listServerPort=list1[1]
                listServerUser=list1[2]	
                //创建文件夹
                sh "ansible  ${listServerIP}  -u ${listServerUser} -m  shell -a  'mkdir -p /tmp/${serviceName}/  mode=0755' "
                //批量cpoy war包
                sh "ansible  ${listServerIP}  -u ${listServerUser} -m  copy -a  'src=${WORKSPACE}/${download_path} dest=/tmp/${serviceName}/ mode=0755' "	
            }
    }
}

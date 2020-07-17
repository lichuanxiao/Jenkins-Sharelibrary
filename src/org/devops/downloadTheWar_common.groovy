def  call(String servicename='',String batchDeployServer='',String warName=''){ 
	if(BatchDeployServer.length() != 0 ){
        //从制品库下载包
        echo "从制品库下载包"
        sh "curl --user '${nexusRawUsernamePassword}'  --o  ${warName}  http://119.3.223.138:8081/repository/raw-devops/devops/${params.nexusRawRepository}"
        //下载脚本
        sh "/usr/bin/git clone git@github.com:ghl1024/Jenkinsfile.git"	                         
        def serverlist=BatchDeployServer.split('\\|')
        for(values in serverlist){
            //values=serverlist[0]
            list1=values.split(',') 
            print(list1[0]+" "+list1[1]+" "+list1[2])						  
            listServerIP=list1[0]
            listServerPort=list1[1]
            listServerUser=list1[2]	
            //批量拷贝
            sh "ansible ${env.serverIP} -u ${env.serverName} -m  shell -a  'mkdir -p /home/${env.serverName}/tmp'"
            sh "ansible ${env.serverIP} -u ${env.serverName} -m  copy -a  'src=${WORKSPACE}/Jenkinsfile/jenkins dest=/home/${env.serverName} mode=0755'"
            sh "ansible ${env.serverIP} -u ${env.serverName} -m  copy -a  'src=${WORKSPACE}/${env.WarName} dest=/home/${env.serverName}/tmp/${env.WarName} mode=0755'"  
        }	
    }						        
}

def call(String yanzheng='',String servicename='',String batchDeployServer='',String DeployShellName='',String warPath='',String WarName=''){ 
    def serverlistTemp=getDeployServer(yanzheng,batchDeployServer)
        def bsn=DeployShellName
        //移动文件并发布
        for(String values: serverlistTemp){
            list1=values.split(',') 
            print("deployTheWar : "+list1[0]+" "+list1[1]+" "+list1[2])						  
            listServerIP=list1[0]
            listServerPort=list1[1]
            listServerUser=list1[2]
            sh "ansible  ${listServerIP}  -u ${listServerUser} -m  shell -a  ' source ~/.bash_profile ; mv /tmp/${servicename}/${WarName} ${warPath} ;nohup sh ${bsn}  ' "
        }		
        return " ${batchDeployServer} deploy is comeplete"	        
}

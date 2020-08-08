def call(String servicename='',String serviceNameBatchDeployServer='',String warName=''){ 
    def serverlist=serviceNameBatchDeployServer.split('\\|')
        for(values in serverlist){
            //values=serverlist[0]
            list1=values.split(',') 
            print(list1[0]+" "+list1[1]+" "+list1[2])						  
            listServerIP=list1[0]
            listServerPort=list1[1]
            listServerUser=list1[2]	
            //创建文件夹
            sh "ansible  ${listServerIP}  -u ${listServerUser} -m  shell -a  'mkdir -p /tmp/${serviceName}/ ' "
            //批量cpoy war包
            sh "ansible  ${listServerIP}  -u ${listServerUser} -m  copy -a  'src=${WORKSPACE}/${warName} dest=/tmp/${serviceName}/ ' "
        }
        return " ${servicename} copy is comeplete"     	        
}

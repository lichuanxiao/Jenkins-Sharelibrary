def  call(String yanzheng='',String batchDeployServer='',int timeSize=1){ 
        //服务健康检查
    def serverlistTemp=getDeployServer(yanzheng,batchDeployServer)
		for(String values: serverlistTemp){    
			list1=values.split(',') 
            print("check the service :"+list1[0]+" "+list1[1]+" "+list1[2])						  
            listServerIP=list1[0]
            listServerPort=list1[1]
                timeout(timeSize){
                    waitUntil{
                        script{
                            def r=sh script: "curl http://\'${listServerIP}\':\'${listServerPort}\' ",returnStatus: true 
                            return (r==0)
                        }			
                    }
                }
        }
		return "check the service is health : ${batchDeployServer} "        
}

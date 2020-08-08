def call(String batchDeployServer=''){ 
    print("transformation_delimiter -----  "+batchDeployServer)
    def serverlist=batchDeployServer.split(',')
    def serverlistTemp=""
        for(int i=0;i<serverlist.length;i++){     
            if(i>0){
                serverlistTemp= serverlistTemp + "|" + serverlist[i].replaceAll('\\|',',')
            }
            if(i==0){
                serverlistTemp= serverlist[0].replaceAll('\\|',',')
            }
        }
    print "serverlist all : ${serverlistTemp}"
    return serverlistTemp.replaceAll('"','')  
}

def call(String yanzheng='',String batchDeployServer=''){ 
    print("getDeployServer ----- "+yanzheng+" "+batchDeployServer)
    def serverlist=batchDeployServer.split('\\|')
    def serverlistTemp=new ArrayList()
        if( yanzheng == 'yanzheng'){
            echo "${serverlist[0]}"
            serverlistTemp.add(serverlist[0])
        }
        if( yanzheng == 'other') {
            for(int i=1;i<serverlist.length;i++){
                serverlistTemp.add(serverlist[i])
            }	   
            print "serverlist other : ${serverlistTemp}"
        }
        if ( yanzheng == 'all'){
           for(int i=0;i<serverlist.length;i++){
               serverlistTemp.add(serverlist[i])
           }
           print "serverlist all : ${serverlistTemp}"
        }   
    return serverlistTemp  
}

def  call(String warName='',String targetName=''){ 
		    rtServer (
                    id: "artifactory-admin",
                    url: "http://172.16.136.13:8081/artifactory",
                    credentialsId: "artifactory-admin",
                )
               rtUpload(
				   serverId: "artifactory-admin",
				   spec:"""{"files":[{"pattern":"${warName}","target":"${targetName}"}]}"""
				)
				rtPublishBuildInfo (
                    serverId: "artifactory-admin"
                )			
               archiveArtifacts artifacts: "${warName}", fingerprint: true					
       
}

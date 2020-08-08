def call(String warName='',String targetName=''){ 
    rtServer (
        id: "ARTIFACTORY_SERVER",
        url: "http://172.16.136.13:8081/artifactory",
        credentialsId: "artifactory-admin",
    )
    rtUpload(
        serverId: "ARTIFACTORY_SERVER",
        spec:"""{"files":[{"pattern":"${warName}","target":"${targetName}"}]}"""
    )
    rtPublishBuildInfo (
        serverId: "ARTIFACTORY_SERVER"
    )			
    archiveArtifacts artifacts: "${warName}", fingerprint: true
 }

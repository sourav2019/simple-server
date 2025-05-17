job("node js project ver 2"){
    description("this project will clone node js proj and build and push it to docker hub")
     scm {
        git('https://github.com/sourav2019/simple-server.git','main') { node -> 
            node / gitConfigName('sourav2019')
            node / gitConfigEmail('souravsaha113@yahoo.com')
        }
    }
    wrappers {
          nodejs('node 16')
          credentialsBinding {
            usernamePassword('USERNAME_DOC', 'PASSWORD_DOC','dockerhubcred')
        }
    }
    steps{
        shell('npm install')
        shell('docker login -u ${USERNAME_DOC} -p ${PASSWORD_DOC}')
        dockerBuildAndPublish {
            repositoryName('prosourav49/nodejs-jenkins')
            tag('${BUILD_NUMBER}')
            registryCredentials('dockerhubcred')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
         shell('docker logout')
    }
}
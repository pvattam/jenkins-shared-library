def call(){
    node ('workstation'){

        sh "find . | sed -e '1d' |xargs rm -rf"

        if(env.TAG_NAME ==~ ".*") {
            env.branch_name = "refs/tags/${env.TAG_NAME}"
        }else {
            env.branch_name = "${env.BRANCH_NAME}"
        }
        sh 'env'
        stage('Code Checkout'){
            checkout scmGit(
                    branches: [[name: "${branch_name}"]],
                    userRemoteConfigs: [[url: "https://github.com/pvattam/expense-backend.git"]]
            )
            sh 'cat Jenkinsfile'
        }


        stage('Compile'){}

        if(env.BRANCH_NAME == 'main'){
            stage('Build'){}
        } else if(env.BRANCH_NAME ==~ "PR.*") {
            stage('Test Cases'){}
            stage('Integration Test Cases'){}
        } else if(env.TAG_NAME ==~ ".*") {
            stage('Build') {}
            stage('Release App') {}
        }
        else {
            stage('Test Cases'){}
        }
//        stage('Code Checkout'){}
//        stage('Compile'){}
//        stage('Test Cases'){}
//        stage('Integration Test Cases'){}
//        stage('Build'){}
//        stage('Release App'){}
    }
}

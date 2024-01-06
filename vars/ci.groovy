def AWS_SSM_PARAM(param_name) {
    def OUTPUT = sh ( script: "aws ssm get-parameter --name ${param_name} --with-decryption --query 'Parameter.Value' --output text", returnStdout: true).trim()
    return(OUTPUT)
}

def call(){
    node ('workstation'){

        sh "find . | sed -e '1d' |xargs rm -rf"

        if(env.TAG_NAME ==~ ".*") {
            env.branch_name = "refs/tags/${env.TAG_NAME}"
        }else {
            if (env.BRANCH_NAME ==~ "PR-.*"){
                env.branch_name = "${env.CHANGE_BRANCH}"
            }else{
                env.BRANCH_NAME = "${env.BRANCH_NAME}"
            }

        }

        stage('Code Checkout'){
            checkout scmGit(
                    branches: [[name: "${branch_name}"]],
                    userRemoteConfigs: [[url: "https://github.com/pvattam/${repo_name}.git"]]
            )
            sh 'cat Jenkinsfile'
        }

        if(app_type == "nodejs"){
            stage('Download Dependencies'){
                sh 'npm install'
            }
        }


        if(env.JOB_BASE_NAME ==~ "PR.*") {
            sh 'echo PR'
            stage('Test Cases'){
                //sh 'npm test'
            }
            stage('Code Quality'){

                env.SONAR_TOKEN = AWS_SSM_PARAM(param_name: 'sonar.token')
                sh 'sonar-scanner -Dsonar.host.url=http://18.215.231.226:9000 -Dsonar.login=${SONAR_TOKEN} -Dsonar.projectKey=expense-backend'

            }
        }else if(env.BRANCH_NAME == 'main'){
            sh 'echo main'
            stage('Build'){}
        }else if(env.TAG_NAME ==~ ".*") {
            sh 'echo TAG'
            stage('Build') {}
            stage('Release App') {}
        }else {
            sh 'echo branch'
            stage('Test Cases'){
                //sh 'npm test'
                //These test cases in organization is ideally written and we dont skip this step. Since it is a LAB, we are moving further.
            }
        }

    }
}

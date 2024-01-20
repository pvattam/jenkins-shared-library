def codeCheckout(){
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
        sh 'cat Jenkins'
    }
}
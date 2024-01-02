def call(){
    node ('workstation'){
        stage('Code Checkout'){}
        stage('Compile'){}

        if(BRANCH_NAME == 'main'){
            stage('Build'){}
        } else if(BRANCH_NAME ==~ "PR.*") {
            stage('Test Cases'){}
            stage('Integration Test Cases'){}
        }else {
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

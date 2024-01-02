def call(){
    node ('workstation'){
        sh 'env'
        if(BRANCH_NAME == 'main'){
            stage('Code Checkout'){}
            stage('Compile'){}
            stage('Build'){}
        } else if(BRANCH_NAME ==~ "PR.*") {
            stage('Code Checkout'){}
            stage('Compile'){}
            stage('Test Cases'){}
            stage('Integration Test Cases'){}
        }else {
            stage('Code Checkout'){}
            stage('Compile'){}
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

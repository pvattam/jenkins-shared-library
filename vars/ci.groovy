def call(){
    pipeline {
        agent{
            node {label 'workstation'}
        }

        stages{

            stage ('Compile'){
                steps {
                    echo 'Compile'
                }
            }

            stage ('Test Cases'){
                steps {
                    echo 'Test Cases'
                }
            }

            stage ('Integration Test Cases'){
                steps {
                    echo 'Integration Test Cases'
                }
            }

            stage ('Build'){
                steps {
                    echo 'Build'
                }
            }

            stage ('Release Apply'){
                steps {
                    echo 'Release Apply'
                }
            }

        }
    }
}
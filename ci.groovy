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

            stage ('Build'){
                steps {
                    echo 'Build'
                }
            }

            stage ('Release'){
                steps {
                    echo 'Release'
                }
            }

        }
    }
}
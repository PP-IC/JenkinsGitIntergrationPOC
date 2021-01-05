def RE_ENV = "qa2";
    
pipeline {
    agent any
    
    stages {
        stage('test') {
            steps {
                echo 'hello'
            }
        }
        stage('test1') {
            steps {
                echo 'TEST1'
            }
        }
        stage('test3') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {
                        echo 'I only execute on the main branch'
                    } else {
                        echo 'I execute elsewhere'
                    }
                    
                    if (RE_ENV == 'qa2') {
                        echo 'I only execute on the QA2 Env'
                        sh "java -jar SamplePOC_SS_Env_V1.jar ComponentBuilder_test POC chrome X94Q1"
                        
                    }
                    if (RE_ENV == 'qa3') {
                        echo 'I only execute on the QA3 Env'
                    }
                    if (RE_ENV == 'test') {
                        echo 'I only execute on the test Env'
                    }
                    if (RE_ENV == 'dev') {
                        echo 'I only execute on the dev Env'
                    }
                }
            }
        }
    }
}

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
                        IF EXIST Screenshots rmdir /s /q Screenshots
                        move .\*.zip Archive
                        java -jar SamplePOC_SS_Env_V1.jar "SeleniumModule" Smoke chrome X94Q1
                        powershell Compress-Archive Screenshots Screenshots_Build_%BUILD_NUMBER%.zip
                        powershell Compress-Archive test-output test-output_Build_%BUILD_NUMBER%.zip
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

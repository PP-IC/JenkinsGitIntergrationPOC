def RE_ENV = "X94Q1";
    
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
                    if (env.BRANCH_NAME == 'main') {
                        echo 'I only execute on the main branch'
                    } else {
                        echo 'I execute elsewhere'
                    }
                    
                    if (RE_ENV == 'X94Q1') {
                        echo 'I only execute on the QA2 Env'
                        bat """
                        java -jar SamplePOC_SS_Env_V1.jar ComponentBuilder_test POC chrome X94Q1
                        """
                        
                    }
                    if (RE_ENV == 'A95Q3') {
                        echo 'I only execute on the A95Q3 Env'
                        bat """
                        java -jar SamplePOC_SS_Env_V1.jar ComponentBuilder_test POC chrome A95Q3
                        """
                        
                    }
                    if (RE_ENV == 'A951Q3') {
                        echo 'I only execute on the A951Q3 Env'
                        bat """
                        java -jar SamplePOC_SS_Env_V1.jar ComponentBuilder_test POC chrome A951Q3
                        """
                        
                    }
                    if (RE_ENV == 'dev') {
                        echo 'I only execute on the dev Env'
                    }
                }
            }
        }
    }
}

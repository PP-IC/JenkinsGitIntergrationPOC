pipeline {
    agent any
    
    parameters { 
        choice(name: 'RE_ENV', choices: ["dev", "test", "qa2", "qa3"], description: 'Available environment list')
        booleanParam(defaultValue: true, name: 'Run_Smoke', description: 'Run Smoke Test Cases')
    }

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
                    
                    if ($params.RE_ENV == 'qa2') {
                        echo 'I only execute on the QA2 Env'
                    }
                    if ($params.RE_ENV == 'qa3') {
                        echo 'I only execute on the QA3 Env'
                    }
                    if ($params.RE_ENV == 'test') {
                        echo 'I only execute on the test Env'
                    }
                    if ($params.RE_ENV == 'dev') {
                        echo 'I only execute on the dev Env'
                    }
                }
            }
        }
    }
}

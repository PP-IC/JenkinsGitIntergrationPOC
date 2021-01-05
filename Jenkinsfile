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
                        sh 'IF EXIST "Screenshots" rmdir /s /q "Screenshots"'
                        echo 'Deleted Screenshots folder'
                        sh 'IF EXIST "*.zip" del "*.zip"'
                        echo 'Deleted Zip folders'
                        bat """
                        java -jar SamplePOC_SS_Env_V1.jar ComponentBuilder_test POC chrome X94Q1
                        """
                        powershell 'Compress-Archive Screenshots Screenshots_Build_${BUILD_NUMBER}.zip'
                        powershell 'Compress-Archive test-output test-output_Build_${BUILD_NUMBER}.zip'
                        s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'ic-qa-poc/Screenshot-Jenkins/${JOB_NAME}-${BUILD_NUMBER}', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: true, selectedRegion: 'ap-south-1', showDirectlyInBrowser: false, sourceFile: '*.zip', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'TestName', userMetadata: []

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

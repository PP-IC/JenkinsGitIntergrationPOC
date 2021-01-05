def re_env = 'X951Q3';
    
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
                    
                    if (re_env == 'X951Q3') {
                        echo 'I only execute on the QA2 Env'
                        //sh 'IF EXIST "Screenshots" rmdir /s /q "Screenshots"'
                        //echo 'Deleted Screenshots folder'
                        //sh 'IF EXIST "*.zip" del "*.zip"'
                        //echo 'Deleted Zip folders'
                        bat """
                        IF EXIST Screenshots rmdir /s /q Screenshots
                        IF EXIST *.zip del *.zip
                        java -jar SamplePOC_SS_Env_V1.jar TC_GTN_Snapshot_Verification Smoke_Suite chrome ${re_env}
                        powershell Compress-Archive Screenshots Screenshots_Build_${env.BUILD_NUMBER}.zip
                        powershell Compress-Archive test-output test-output_Build_${env.BUILD_NUMBER}.zip
                        """
                        //powershell 'Compress-Archive Screenshots Screenshots_Build_$env.BUILD_NUMBER.zip'
                        //powershell 'Compress-Archive test-output test-output_Build_$env.BUILD_NUMBER.zip'
                        //s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'ic-qa-poc/Screenshot-Jenkins/${JOB_NAME}-${BUILD_NUMBER}-${re_env}', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: true, selectedRegion: 'ap-south-1', showDirectlyInBrowser: false, sourceFile: '*.zip', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'TestName', userMetadata: []

                        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'efaadd0b-0b0f-47c1-a6e3-5a6b88e40b74', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                        //sh "aws s3 mb s3://ic-qa-poc/Screenshot-Jenkins/${JOB_NAME}-${BUILD_NUMBER}-${re_env}"
                        sh "aws s3 cp *.zip s3://ic-qa-poc/Screenshot-Jenkins/${JOB_NAME}-${BUILD_NUMBER}-${re_env}"

                        }
                        
                    }
                    if (re_env == 'A95Q3') {
                        echo 'I only execute on the A95Q3 Env'
                        bat """
                        java -jar SamplePOC_SS_Env_V1.jar ComponentBuilder_test POC chrome ${re_env}
                        """
                        
                    }
                    if (re_env == 'X94Q1') {
                        echo 'I only execute on the X94Q1 Env'
                        bat """
                        java -jar SamplePOC_SS_Env_V1.jar ComponentBuilder_test POC chrome ${re_env}
                        """
                        
                    }
                    if (re_env == 'dev') {
                        echo 'I only execute on the dev Env'
                    }
                }
            }
        }
    }
}

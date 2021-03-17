def re_env = 'QA1'; 
//re_env to use from choices RE_ENV [dev, qa1, qa2, qa3, qa4, perfdev]
//add booleanParam for "Smoke Execution" with defaultValue: true
//if "Smoke Execution" option is selected --> show choice for browser [chrome, edge, ie]   
def re_browser = 'chrome';
def re_group = 'Smoke';
def re_module = 'SeleniumModule';
def build_status= '';

pipeline {
    agent any
    
    stages {
        stage("App and DACPAC") {
            steps {
                echo 'Executed Stage1 App and DACPAC.'
            }
        }
        stage('DB Restore') {
            steps {
                echo 'Executed Stage2 DB Restore'
            }
        }
        stage('Data Setup Migration') {
            steps {
                echo 'Executed Stage3 Data Setup and Migration'
            }
        }
         stage('Jasper Deployments') {
            steps {
                echo 'Executed Stage4 Jasper Deployments'
                script {
                    build_status = 'Success';
                }
                
            }
        }
        stage('Smoke Execution') {
            steps {
                script {
                    if (build_status == 'Success') {
                    if (env.BRANCH_NAME == 'master') {
                        echo 'Executing code from master branch'
                    } else {
                        //echo 'I execute elsewhere'
                    }
                    
                        echo "Aautomated Smoke Test started on the ${re_env} Env"
                        
                        //Clear old artifacts
                        //java -jar SamplePOC_SS_Env_V1.jar TC_GTN_Snapshot_Verification Smoke_Suite ${re_browser} ${re_env}
                        bat """
                        IF EXIST Screenshots rmdir /s /q Screenshots
                        IF EXIST *.zip del *.zip
                        java -jar SamplePOC_SS_V2.jar ${re_module} ${re_group} ${re_browser}
                        powershell Compress-Archive Screenshots Screenshots_Build_${env.BUILD_NUMBER}.zip
                        powershell Compress-Archive test-output test-output_Build_${env.BUILD_NUMBER}.zip
                        """

                    //Upload artifacts to AWS S3 Bucket
                    s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: "ic-qa-poc/Screenshot-Jenkins/${JOB_NAME}-${BUILD_NUMBER}-${re_env}", excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: true, selectedRegion: 'ap-south-1', showDirectlyInBrowser: false, sourceFile: '*.zip', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'TestName', userMetadata: []
   
                }
                }
            }
        }
    }
    post {
        always {
            //Email Notification of Smoke Result
            emailext attachLog: true, attachmentsPattern: 'test-output/emailable-report.html', body: '''Hi, Please see automation smoke suite execution report as below:
            ${FILE, path="test-output/emailable-report.html"}''', subject: '$DEFAULT_SUBJECT', to: 'ppandit@integrichain.com'
        }
    }
}

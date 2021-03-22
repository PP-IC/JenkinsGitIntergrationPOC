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
    node('master') {
    script{
                                    // Get file using input step, will put it in build directory
                                    print "=================Please upload your property files ====================="
                                    def inputFile = input message: 'Upload file', parameters: [file(name: 'global.properties')]
                                    // Read contents and write to workspace
                                    writeFile(file: 'global.properties', text: inputFile.readToString())
                                    // Stash it for use in a different part of the pipeline
                                    stash name: 'data', includes: 'global.properties'
                            }
    }
    parameters{
        choice(choices: ['SharedDev', 'QA1', 'QA2', 'QA3', 'QA4', 'QA5', 'SIT', 'UAT', 'PROD', 'PerfDev', 'Demo'], description: 'Please select the Environment to Run the Automation Suite.', name: 'Environment') 
        choice(choices: ['chrome', 'Edge', 'IE'], description: 'Please select the Browser to Run the Automation Suite.', name: 'Browser') 
        choice(choices: ['Tenant1', 'Tenant2'], description: 'Please select tenant', name: 'Tenant') 
        choice(choices: ['Org1', 'Org2'], description: 'Please select organization', name: 'Org') 
        string(defaultValue: 'ComponentBuilder_test,FormulaBuilder_test,TechConfiguration_test,AssumptionPackage_test,GTNNewSnapshots_test,T09_Editors_TC_18to27,T13_Metric_Summary_TC_94_to_109,T14_TSE_TC_110_to_116_TC_119_120,InventorySummaryList,T01_PCM_Pricing_TC_01_to_10', description: 'Please select class files to be executed', name: 'ClassFiles', trim: false) 
        string(defaultValue: 'GTN Smoke,Multi Org Smoke', description: 'Please select groups to be executed', name: 'Groups', trim: false) 
        string(defaultValue: '', description: 'Please provide Analyst UserName', name: 'AnalystUserName', trim: true) 
        string(defaultValue: '', description: 'Please provide Analyst Password', name: 'AnalystPassword') 
        string(defaultValue: '', description: 'Please provide Manager UserName', name: 'ManagerUserName', trim: true) 
        string(defaultValue: '', description: 'Please provide Manager Password', name: 'ManagerPassword')
        //file(description: 'Please input test data file', name: 'TestData.properties')

    }
    
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
                    
                    // Read contents and write to workspace
                     //writeFile(file: 'TestData.properties', text: inputFile.readToString())
                   // powershell 'Get-Content .\\TestData.properties'
                    
                        echo "Aautomated Smoke Test started on the ${params.Environment} Env"
                        
                        //Clear old artifacts
                        //java -jar SamplePOC_SS_Env_V1.jar TC_GTN_Snapshot_Verification Smoke_Suite ${re_browser} ${re_env}
                        //java -jar SamplePOC_SS_V2.jar ${re_module} ${re_group} ${re_browser}
                        bat """
                        IF EXIST allure-report rmdir /s /q allure-report
                        cd "d-Rive UI Test Automation Framework\\daVIZta Automation Framework"
                        IF EXIST Screenshots rmdir /s /q Screenshots
                        IF EXIST *.zip del *.zip
                        java -javaagent:"C:\\Users\\pandipr\\.m2\\repository\\org\\aspectj\\aspectjweaver\\1.9.2\\aspectjweaver-1.9.2.jar" -DanalystUserName=${params.AnalystUserName} -DanalystPassword=${params.AnalystPassword} -DmanagerUsername=${params.ManagerUserName} -DmanagerPassword=${params.ManagerPassword} -jar dRiveAutomationSuiteR96_allure.jar ${params.ClassFiles} ${params.Groups} ${params.Browser} ${params.Environment} ${params.Tenant} ${params.Org}
                        powershell Compress-Archive Screenshots Screenshots_Build_${env.BUILD_NUMBER}.zip
                        powershell Compress-Archive test-output test-output_Build_${env.BUILD_NUMBER}.zip
                        allure generate -c
                        """

                    //Upload artifacts to AWS S3 Bucket
                        //allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                        //s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'ic-qa-poc/Screenshot-Jenkins/${JOB_NAME}-${BUILD_NUMBER}', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: false, selectedRegion: 'ap-south-1', showDirectlyInBrowser: false, sourceFile: '**/allure-report/', storageClass: 'STANDARD', uploadFromSlave: true, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'TestName', userMetadata: []
                    //s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: "ic-qa-poc/Screenshot-Jenkins/${JOB_NAME}-${BUILD_NUMBER}-${re_env}", excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: true, selectedRegion: 'ap-south-1', showDirectlyInBrowser: false, sourceFile: '*.zip', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'TestName', userMetadata: []
                }
            }
        }
    }
    post {
        always {
            //Upload artifacts to AWS S3 Bucket
            allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'ic-qa-poc/AllureReports/${JOB_NAME}-${BUILD_NUMBER}', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: false, selectedRegion: 'ap-south-1', showDirectlyInBrowser: false, sourceFile: '**/allure-report/', storageClass: 'STANDARD', uploadFromSlave: true, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'TestName', userMetadata: []
  
            //Cleanup allure files
            bat """
            IF EXIST allure-results cd allure-results
            for %%i in (*.*) do if not %%i==environment.properties if not %%i==categories.json del %%i
                        
            """
            //Email Notification of Smoke Result
            //emailext attachLog: true, attachmentsPattern: 'test-output/emailable-report.html', body: '''Hi, Please see automation smoke suite execution report as below:
            //${FILE, path="test-output/emailable-report.html"}''', subject: '$DEFAULT_SUBJECT', to: 'ppandit@integrichain.com'
            emailext body: 'Report URL - https://ic-qa-poc.s3.ap-south-1.amazonaws.com/AllureReports/${JOB_NAME}-${BUILD_NUMBER}/allure-report/index.html', subject: '$DEFAULT_SUBJECT', to: 'ppandit@integrichain.com'
        }
    }
}

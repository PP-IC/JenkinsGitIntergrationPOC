def env_name  = env.BRANCH_NAME  == 'master' ? 'prod' : env.BRANCH_NAME == 'uat' ? 'uat' : env.BRANCH_NAME == 'test' ? 'test' : env.BRANCH_NAME == 'sre-build-development' ? 'dev' : env.BRANCH_NAME;
def project_name = 'gtn-app';
def war_container = 'gtn-build';
def REGION = 'us-east-1';
def notificationsEnabled = true;
def notificationChannel = '#gtn_sprint_team';
def attachments = """[{\"image_url\":\"https://media.giphy.com/media/gyYJYyCPezUWI/giphy.gif\"}]"""
def s3folder = "s3_files";
def jSecret = '';
def jWinSecret = '';
def jcustomers = '';
def secustomers = '';
def jlmasterdb = '';
def jwmasterdb = '';
def desire_tasks = '1';
def jCommand = "DEPLOY_JASPER_REPORTS";
def jPath= "/Build/js-catalog-exp.zip";
def jDB_port ='1433';
def etl_path = 'Build/Utils/etl_script';

pipeline{

    agent none
    parameters { 
        choice(name: 'RE_ENV', choices: ["dev", "test", "qa2", "qa3"], description: 'Available environment list')
        booleanParam(defaultValue: false, name: 'Run_Smoke', description: 'Run Smoke Test Cases')
        choice(name: 'Source_DB', choices: ["BASE", "PMU", "PMQ"], description: 'Available DB to restore')
        string(defaultValue: 'None',description: 'Source DB Prefix',name: 'Sprefix')
        string(defaultValue: 'None',description: 'Destination DB Prefix',name: 'Dprefix')

        
    }

    stages{
        stage("SmokeExecution"){
            
            when { 
                expression { params.Run_Smoke }
                }
            steps {
              echo 'Smoke Execution'
              //
              //
              //
              
            }
        }
        
    }
    post {
        always {
            echo 'Post Complete'
        }
    }
  
}

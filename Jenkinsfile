pipeline{

    agent none
    parameters { 
        choice(name: 'RE_ENV', choices: ["dev", "test", "qa2", "qa3"], description: 'Available environment list')
        booleanParam(defaultValue: true, name: 'Run_Smoke', description: 'Run Smoke Test Cases')
        

        
    }

    stages{
        stage("SmokeExecution"){
             echo 'Smoke Execution'
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

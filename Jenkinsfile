pipeline {
    agent any

    stages {
        stage ('Build Image'){
            steps {
                script {
                    
                    dockerapp = docker.build("quarkus", "-f ${WORKSPACE}/Dockerfile ${WORKSPACE}")
                }
                echo 'Iniciando a pipeline'
            }
        }
    }
}
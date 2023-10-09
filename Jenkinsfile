pipeline {
    agent any

    stages {
        stage ('Build Image'){
            steps {
                script {

                    dockerapp = docker.build("quarkus", '-f ./src/Dockerfile ./src')
                }
                echo 'Iniciando a pipeline'
            }
        }
    }
}
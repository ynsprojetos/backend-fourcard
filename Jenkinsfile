pipeline {
    agent any

    stages {
        stage ('Build Image'){
            steps {
                script {
                    dockerapp = docker.build("fourcard-image", '-f ./src/Dockerfile ./src')
                }
                echo 'Iniciando a pipeline'
            }
        }
    }
}
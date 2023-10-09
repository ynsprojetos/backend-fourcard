pipeline {
    agent any

    stages {
        stage ('Build Image'){
            steps {
                script {
                    dockerapp = docker.build("fourcard-image", "-f Dockerfile ./src")

                }
                echo 'Iniciando a pipeline'
            }
        }
    }
}
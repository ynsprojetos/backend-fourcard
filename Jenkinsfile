pipeline {
    agent any

    stages {
        stage ('Build Image'){
            steps {
                script {
                    dockerapp = docker.build("quarkus", "-t .")

                }
                echo 'Iniciando a pipeline'
            }
        }
    }
}
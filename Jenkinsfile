pipeline {
    agent any

    stages {
        stage ('Build Image'){
            steps {
                script {
                    dockerapp = docker.build("fourcard-image", "-f ${WORKSPACE}/Dockerfile .")

                }
                echo 'Iniciando a pipeline'
            }
        }
    }
}
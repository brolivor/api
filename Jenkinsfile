pipeline {
    agent any
    tools {
        maven 'maven_v3'
        jdk 'openjdk-18'
    }
    environment {
        PATH="/opt/maven/bin:$PATH"
        DOCKEHUB_CREDENTIALS = credentials('dockerhub')
    }
    stages {
        stage("Build & SonarQube analysis") {
            steps {
                withSonarQubeEnv('SonarQube Server') {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
        stage("Docker Build") {
            steps {
                sh 'docker build . -t madhurm54/api-docker:latest'
                sh 'echo $DOCKEHUB_CREDENTIALS_PSW | docker login -u $DOCKEHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push madhurm54/api-docker:latest'
            }
        }
        stage("Deploy to Kubernetes") {
            steps {
                sshagent(['k8s-master']) {
                    sh 'scp -o StrictHostKeyChecking=no services.yaml pods.yaml node@192.168.1.157:/home/node'
                    script {
                        try {
                            sh 'ssh node@192.168.1.157 kubectl apply -f .'
                        } catch (error) {
                            sh 'ssh node@192.168.1.157 kubectl create -f .'
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}
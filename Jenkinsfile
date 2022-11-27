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
                sshagent(['k3s-node-1']) {
                    sh 'scp -o StrictHostKeyChecking=no services.yaml pods.yaml root@192.168.1.156:/root'
                    script {
                        try {
                            sh 'ssh root@192.168.1.156 kubectl apply -f .'
                        } catch (error) {
                            sh 'ssh root@192.168.1.156 kubectl create -f .'
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
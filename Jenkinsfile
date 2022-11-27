pipeline {
    agent any
    tools {
        maven 'maven_v3'
        jdk 'openjdk-18'
    }
    environment {
        PATH="/opt/maven/bin:$PATH"
        imageName = "api-docker"
        registryCredentials = "nexus"
        registry = "http://192.168.1.154:8085/"
    }
    stages {
        stage("GIT Clone") {
            steps {
                git branch: 'Nov_2022', url: 'https://github.com/brolivor/api.git'
            }
        }
        stage("Build & SonarQube analysis") {
            steps {
                withSonarQubeEnv('SonarQube Server') {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
        stage("Docker Build") {
            steps {
                script {
                    dockerImage = docker.build imageName
                }
            }
        }
    }
}
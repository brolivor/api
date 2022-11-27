pipeline {
    agent any
    tools {
        maven 'maven_v3'
        jdk 'openjdk-18'
    }
    steps {
        stage("GIT Clone") {
            git branch: 'Nov_2022', url: 'https://github.com/brolivor/api.git'
        }
        stage("Build & SonarQube analysis") {
            agent any
            steps {
                withSonarQubeEnv('SonarQube Server') {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
    }
}
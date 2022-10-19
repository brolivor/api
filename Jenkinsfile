pipeline {
    agent any
    environment {
        PATH = "$PATH:/opt/maven/bin"
    }
    stages {
       stage('GetCode') {
            steps {
                git 'https://github.com/brolivor/Spring-Learning.git'
            }
       }
       stage('Build') {
            steps {
                sh 'mvn clean package'
            }
       }
       stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube v9.7') {
                sh "mvn sonar:sonar"
                }
            }
       }
    }
}
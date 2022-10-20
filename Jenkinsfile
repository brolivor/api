pipeline {
    agent any
    environment {
        PATH = "$PATH:/opt/maven/bin"
        JAVA_HOME = "/opt/jdk-18.0.2.1"
    }
    stages {
       stage('GetCode') {
            steps {
                git 'https://github.com/brolivor/api.git'
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
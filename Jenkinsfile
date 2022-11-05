pipeline {
    agent any
    tools {
        maven 'maven_v3'
    }
    stages {
        stage("build & SonarQube analysis") {
            agent any
            steps {
                withSonarQubeEnv('SonarQube Server') {
                sh 'mvn clean package sonar:sonar'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
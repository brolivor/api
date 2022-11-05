pipeline {
    agent any
    tools {
        maven 'maven_v3'
        jdk 'openjdk-18'
    }
    stages {
        stage("Build & SonarQube analysis") {
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
        stage("Uploading War to Nexus") {
            steps {
                nexusArtifactUploader artifacts:
                [
                    [   artifactId: 'api',
                        classifier: '',
                        file: 'target/api-0.0.1-SNAPSHOT.war',
                        type: 'war'
                    ]
                ],
                credentialsId: '',
                groupId: 'com.curioushead',
                nexusUrl: '34.151.111.234',
                nexusVersion: 'nexus3',
                protocol: 'http',
                repository: 'http://34.151.111.234:8081/repository/spring-boot-api-release/',
                version: '0.0.1-SNAPSHOT'
                }
            }
        }
    }
}
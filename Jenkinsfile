pipeline {
    agent any
    tools {
        maven 'maven_v3'
        jdk 'openjdk-18'
    }
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "34.151.111.234:8081"
        NEXUS_REPOSITORY = "spring-boot-api-release"
        NEXUS_CREDENTIAL_ID = "jenkins-nexus"
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
        stage("Publish to Nexus Repository Manager") {
            steps {
                nexusArtifactUploader (
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: '34.151.111.234:8081',
                    groupId: 'com.curioushead',
                    version: '0.0.1-SNAPSHOT',
                    repository: 'spring-boot-api-release',
                    credentialsId: 'jenkins-nexus',
                    artifacts:
                    [
                        [
                            artifactId: 'api',
                            classifier: '',
                            file: 'target/api-0.0.1-SNAPSHOT.war',
                            type: 'war'
                        ]
                    ]
                )
            }
        }
    }
}
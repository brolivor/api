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
        stage("Publish to Nexus Repository Manager") {
            steps {
                nexusPublisher nexusInstanceId: 'nexus-server', nexusRepositoryId: 'spring-boot-api-release', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '/var/lib/jenkins/workspace/Spring_API_Pipeline@2/target/api-0.0.1.war']], mavenCoordinate: [artifactId: 'api', groupId: 'com.curioushead', packaging: 'war', version: '0.0.1']]]
            }
        }
        stage("Deploy to Tomcat") {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'jenkins-tomcat', path: '', url: 'http://34.151.111.234:80/')], contextPath: 'api', war: '/var/lib/jenkins/workspace/Spring_API_Pipeline@2/target/*.war'
            }
        }
    }
}
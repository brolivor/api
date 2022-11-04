pipeline {
    agent any
    tools {
        maven 'maven_v3'
    }
    stages {
        stage("test") {
            steps {
                echo "testing the application..."
                script  {
                    withSonarQubeEnv('SonarQube Server')
                    sh "mvn sonar:sonar"
                    timeout(time: 1, unit: 'HOURS') {
                    def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
        stage("build") {
            steps {
                echo "building the application..."
                sh "mvn clean install"
            }
        }
        stage("deploy") {
            steps {
                echo "deploying the application..."
            }
        }
    }
}
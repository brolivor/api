pipeline {
    agent any
    tools {
        maven 'maven_v3'
        jdk 'openjdk-18'
        git 'git'
    }
    stages {
        stage("GIT Clone") {
            git branch: 'Nov_2022', url: 'https://github.com/brolivor/api.git'
        }
    }
}
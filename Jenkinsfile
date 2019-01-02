  pipeline {
    agent any
    stages {
      stage('Test') {
        steps {
          sh 'node -v'
          sh 'npm prune'
          sh 'npm install'
          sh 'snyk test'
        }
      }
      stage('Build') {
        steps {
          sh 'snyk monitor'
        }
      }
    }
    environment {
      SNYK_TOKEN = credentials('SNYK_TOKEN')
    }
  }

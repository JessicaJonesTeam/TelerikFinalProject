pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'sudo npm install -g snyk'
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

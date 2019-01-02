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
    SNYK_TOKEN = credentials('f20a6329-daff-4dd4-a0ba-94ab3cb84ca0')
  }
}

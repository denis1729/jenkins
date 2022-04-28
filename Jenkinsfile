pipeline {
  agent any

  tools {gradle "gradle_5_6_2"}

  triggers{
      cron("$periodicExecution")
  }

  stages {
    stage('Clone Repository') {
      steps {
        git([url: "$urlRepository",
            branch: "$branch",
            credentialsId: "$userCredential"])
      }
    }

    stage('Execute Automated Test Cases') {
      steps {
          sh "gradle clean build test"
      }
    }

    stage('Cucumber Reports') {
        steps {
            cucumber fileIncludePattern: '**/*.json',
            jsonReportDirectory: 'build/cucumber/'
        }
    }
  }
}
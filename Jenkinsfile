pipeline {
  agent any
    stage('Execute Automated Test Cases') {
      steps {
          sh "gradle build test"
      }
    }

    stage('Cucumber Reports') {
        steps {
            cucumber fileIncludePattern: '*.json',
            jsonReportDirectory: 'cucumber/'
        }
    }
  }
}
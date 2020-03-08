pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''mvn clean
mvn package'''
      }
    }

    stage('Deploy') {
      steps {
        dir(path: 'target')
        sh 'nohup java -jar PollutionBackend-0.0.1-SNAPSHOT.jar &'
      }
    }

  }
}
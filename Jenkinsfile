pipeline {
  agent any
  stages {
    stage('Pull') {
      steps {
        git(poll: true, url: 'https://github.com/awrigh206/PollutionBackend.git', branch: 'master', credentialsId: 'awrigh206')
      }
    }

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
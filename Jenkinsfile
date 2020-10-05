node {
    try {
      def docker = tool name: 'docker', type: 'dockerTool'
      def dockerCMD = "${docker}/bin/docker"

      stage('git checkout') {
          git credentialsId: 'gitPass', url: 'https://github.com/bhar-subhajit/TheSpringBootApp.git'
      }

      stage('build and test') {
          def mavenHome = tool name: 'maven-3', type:'maven'
          def mavenCMD = "${mavenHome}/bin/mvn"
          sh "${mavenCMD} clean package"

      }

      stage('Sonarqube Analysis') {
          def mavenHome = tool name: 'maven-3', type:'maven'
          withSonarQubeEnv('sonar-6') {
              sh "${mavenHome}/bin/mvn sonar:sonar"
          }
      }

      stage('build docker image') {
          sh "${dockerCMD} build -t subhajit1996/java-app-jar:1.0.0 ." 

      }

      stage('push docker image to dockerHub') {
          withCredentials([string(credentialsId: 'DockerPwd', variable: 'dockerPwd')]) {
              sh "${dockerCMD} login -u subhajit1996 -p ${dockerPwd}"
          }
          sh "${dockerCMD} push subhajit1996/java-app-jar:1.0.0"
      }

      stage('launch ec2 intance via ansible playbook') {
          ansiblePlaybook becomeUser: 'ec2-user', credentialsId: 'AWS786', installation: 'ansible-2', playbook: 'task.yml', sudoUser: 'ec2-user'
          // ansiblePlaybook becomeUser: 'admin', credentialsId: 'devAWS', installation: 'ansible-2', playbook: 'task.yml', sudoUser: 'admin'
      }
      def ipAddress = "NULL"
      stage('identify the ip address') {
          def command = 'aws ec2 describe-instances --query "Reservations[*].Instances[*].PublicIpAddress[]"'
          def output = sh script: "${command}", returnStdout:true
          def myIp = output.split('"')
          ipAddress = myIp[1]
          println ipAddress
      }

      stage('install docker on ec2') {
          def dockerIns = 'sudo yum install docker -y'
          sshagent(['AWS786']) {
              sh "ssh -tt -o StrictHostKeyChecking=no ec2-user@${ipAddress} ${dockerIns}"
          }
      }

      stage('start docker service') {
          def dockerSTART = 'sudo service docker start'
          sshagent(['AWS786']) {
              sh "ssh -tt -o StrictHostKeyChecking=no ec2-user@${ipAddress} ${dockerSTART}"
          }
      }

      stage('run docker image') {
          def dockerRUN = 'sudo docker run -p 8888:8080 -d subhajit1996/java-app-jar:1.0.0'
          sshagent(['AWS786']) {
              sh "ssh -tt -o StrictHostKeyChecking=no ec2-user@${ipAddress} ${dockerRUN}"
          }
      }
    }
    catch(err) {
          mail bcc: '', body: "${err}", cc: '', from: '', replyTo: '', subject: 'Attention !! Pipeline Failure (Springboot App)', to: 'subhoanindian@gmail.com'
    }
    
    
}

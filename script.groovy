def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t wael98/demo-app:jma-2.5 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push wael98/demo-app:jma-2.5'
    }
} 

def deployApp() {
     
     echo "deploying the application"
     withCredentials([usernamePassword(credentialsId:'nexus-docker-repo',usernameVariable:'USER',passwordVariable:'PWD')]) {
        sh "echo $PWD | docker login -u $USER --password-stdin localhost:8083"
        sh "docker build -t localhost:8083/java-maven-app:2.5 ."
        sh "docker push localhost:8083/java-maven-app:2.5"
    }
              
          
} 

return this

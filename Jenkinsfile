#! usr/bin/env groovy
@Library ('jenkins-shared-library')

def gv

pipeline {
    agent any
    tools{
        maven "Maven"
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                   buildjar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    buildImage 'wael98/demo-app:jma-3.1'   
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                    gv.deployApp()
                }
            }
        }
    }   
}
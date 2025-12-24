pipeline {
    agent none

    stages {

        /* -------------------------
         *  CHECKOUT
         * ------------------------- */
        stage('Checkout Code') {
            agent any
            steps {
                checkout scm
                echo 'Source code checked out.'
            }
        }

        /* -------------------------
         *  BACKEND (Spring Boot)
         * ------------------------- */
        stage('Backend - Build') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {

                    sh 'mvn clean package -DskipTests'
            }
        }

        stage('Backend - Test') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                    sh 'mvn test'

            }
        }

    }

    post {
        success {
            echo 'backend Build & Tests Succeeded!'
        }
        failure {
            echo 'Pipeline Failed!'
        }
    }
}

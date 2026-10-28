pipeline {
    agent any

    triggers { cron('H */4 * * 1-5') }

    stages {
        stage('build') {
            steps {
                script {
                    if (isUnix()) {
                        sh './gradlew clean build -x test'
                    } else {
                        bat 'gradlew.bat clean build -x test'
                    }
                }
            }
        }

        stage('test') {
            steps {
                script {
                    if (isUnix()) {
                        sh './gradlew test -Dheadless.mode=true'
                    } else {
                        bat 'gradlew.bat test -Dheadless.mode=true'
                    }
                }
            }
        }

        stage('aggregate') {
            steps {
                script {
                    if (isUnix()) {
                        sh './gradlew aggregate'
                    } else {
                        bat 'gradlew.bat aggregate'
                    }
                }
            }
        }

        stage('publish report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: 'Serenity-BDD',
                    reportTitles: ''
                ])
            }
        }
    }
}

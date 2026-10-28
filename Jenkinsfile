pipeline {
    agent any

    triggers { cron('H */4 * * 1-5') }

    parameters {
        string(name: 'USER', defaultValue: 'tu usuario', description: 'Usuario de GitHub')
    }

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
                // La credencial `password-github` debe existir en el servidor Jenkins.
                withCredentials([string(credentialsId: 'password-github', variable: 'PASSWORD')]) {
                    script {
                        if (isUnix()) {
                            sh "./gradlew test -Dgithub-user=${params.USER} -Dpassword=${PASSWORD}"
                        } else {
                            bat "gradlew.bat test -Dgithub-user=${params.USER} -Dpassword=${PASSWORD}"
                        }
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

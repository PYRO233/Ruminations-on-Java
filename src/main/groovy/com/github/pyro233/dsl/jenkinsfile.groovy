package com.github.pyro233.dsl

import static com.github.pyro233.dsl.Dsl.pipeline

/**
 * @Author: https://github.com/wololock/groovy-dsl-quickstart
 * @Date: 2022/8/25 23:06
 */
println '''
   ___                                ___ _            _ _            
  / _ \\_ __ ___   _____   ___   _    / _ (_)_ __   ___| (_)_ __   ___ 
 / /_\\/ '__/ _ \\ / _ \\ \\ / / | | |  / /_)/ | '_ \\ / _ \\ | | '_ \\ / _ \\
/ /_\\\\| | | (_) | (_) \\ V /| |_| | / ___/| | |_) |  __/ | | | | |  __/
\\____/|_|  \\___/ \\___/ \\_/  \\__, | \\/    |_| .__/ \\___|_|_|_| |_|\\___|
                            |___/          |_|                        
'''

pipeline {
    agent any

    environment {
        SOME_NUMBER = 123
        SOME_STRING = "foobar"
    }

    stages {
        stage("Build") {
            steps { env ->
                sh "ls -la"
                sh(script: 'date +%Y-%m-%d', returnStdout: false)
                echo "Groovy rocks!"
                echo "env.SOME_STRING = ${env.SOME_STRING}"
            }
        }
        stage("Test") {
            steps {
                sh "mvn -version"
            }
        }
    }
}
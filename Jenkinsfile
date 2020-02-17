pipeline {
	
	agent any
	
	tools {
        	maven 'MyMaven' 
	    	jdk 'JDK'
    	}
	
	triggers {
		/* every hour  at Zero minutes, 1-28 date, Jan-Nov */
  		cron '0 12 1-28  1-11 *'
  		pollSCM 'H/12 * * * *'
	}	

	stages {
			stage ('Compile stage') {
					steps {
						echo 'Compile RestAssured API maven project'
						bat 'mvn clean compile'
					}
			}
			stage ('Run test stage') {
					steps {
						echo 'Run unit tests'
						bat 'mvn test'
					}
			}	
			stage ('Deploy stage') {
					steps {
						echo 'Run instal'
						bat 'mvn install'
					}
			}
	}
	
	post {
		success {
			mail to: "sureshhs@hotmail.com",
			subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})",
			body: "This email from Jenkins pipeline"		
		}
		unstable {
			mail to: "sureshhs@hotmail.com",
			subject: "UNSTABLE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})",
			body: "This email from Jenkins pipeline"	
		}
		failure {
			mail to: "sureshhs@hotmail.com",
			subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})",
			body: "This email from Jenkins pipeline"
		}	
	}	
}				

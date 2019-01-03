
def branchName = "${env.BRANCH_NAME}"
def buildNumber = "${env.BUILD_NUMBER}"

def binaryStash = "binary.${branchName}.${buildNumber}"
def dockerStash = "docker.${branchName}.${buildNumber}"
def licStash = "lic.${branchName}.${buildNumber}"

def defaultTag = "dev"
def tagsMapping = [master: "latest", production: "production", release: "release"]
def buildImage = (tagsMapping[branchName] != null || params.createImage)
def SNYK_TOKEN = "${env.SNYK_TOKEN}"
node('master') {
    properties([
        parameters([
            booleanParam(
                defaultValue: false,
                description: 'Forces image creation for the current branch.',
                name: 'createImage'
            )
        ])
    ])

    // Get the maven tool.
    // ** NOTE: This 'M3' maven tool must be configured in the global configuration. **
    def mvnHome = tool 'M3'

    stage('Sources') {
        // Checkout code from repository
        checkout scm
    }

    stage('Compiling') {
        sh "${mvnHome}/bin/mvn -s docker/settings.xml clean compile assembly:single"
        sh 'snyk monitor'
        if (buildImage) {
            stash includes: 'target/etl-1.0-SNAPSHOT-jar-with-dependencies.jar', name: binaryStash
            stash includes: 'Dockerfile,docker/start.sh', name: dockerStash
            stash includes: '*.lic,*.p12', name: licStash
        }
    }

    stage('Testing') {
        // -U is for --update-snapshots which forces a check for missing releases
      sh 'node -v'
        sh 'npm prune'
        sh 'npm install'
        sh 'snyk test'
        sh "${mvnHome}/bin/mvn clean test -U"

        // publish JUnit results to Jenkins
        step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
    }

    stage('Code Quality') {
        parallel (
            'pmd' : {
                // static code analysis
                sh "${mvnHome}/bin/mvn site"

                step([$class: 'PmdPublisher', pattern: 'target/pmd.xml'])
            },
            'jacoco': {
                // jacoco report rendering
                step([$class: 'JacocoPublisher', execPattern:'target/jacoco.exec', sourcePattern: 'src/main/java', exclusionPattern: '**/*Test.class'])
            }
        )
    }

    stage('Cleaning Build Artifacts') {
        deleteDir()
    }
}
// Our current jenkins setup says that anything that is NOT explicltly tagget to be build on the master will be build on
// something else. Thus this step will always be build on the slaves and not on the master.
// This is done because our master is running an old version of docker that does not support system prune.


    stage('Cleaning Image Artifacts') {
        deleteDir()
    }
}

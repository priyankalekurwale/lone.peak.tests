# lone.peak.tests
This project contains UI Automation tests for https://www.podium.com/. Tests are executed on Google Chrome Browser 

1. PodiumLandingPageIT : Change property file attribute to run tests either on "local" or "docker". Default is set to local
2. Properties file : env.properties located at /lone.peak.tests
3. runtestenv=local
4. runtestenv=docker
   

Project is shared on Github here.Visibility is set to public 
1. URL : https://github.com/priyankalekurwale/lone.peak.tests
2. To Clone the repo on local, run this command:-> git clone https://github.com/priyankalekurwale/lone.peak.tests.git

# To Run tests on Local Machine from Command line
# Pre-Requisites
Below apps needs to be installed before executing tests
1. Java
2. Maven
4. Chrome Browser should be installed
# Steps to run tests
1. Clone project from Github 
2. Navigate to project directory /lone.peak.tests
3. Verify is maven is accessible from this folder with command : mvn -version
4. Execute this command to run tests:-> mvn test 
5. Test reports directory:-> Project home : lone.peak.tests\target\surefire-reports Open index.html

# To Run tests on Docker Container from Command line
# Pre-Requisites
Below apps needs to be installed before executing tests
1. Java
2. Maven
3. Docker
4. Make sure 4444 port is not in use
# Steps to run tests
1. Clone project from Github 
2. Navigate to project directory /lone.peak.tests
3. Execute command to create docker image (Dockerfile is located in lone.peak.tests folder, this command must be run from this folder):-> docker build -t lonepeak .
4. Validate docker image is created:-> docker images
5. Start container using the image:-> docker run -d -p 4444:4444 -v /dev/shm:/dev/shm lonepeak:latest
6. validate container is running:-> docker ps
3. Execute this command to run tests:-> mvn test 
5. Test reports directory:-> Project home : lone.peak.tests\target\surefire-reports Open index.html


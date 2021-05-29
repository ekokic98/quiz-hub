# quiz-hub
Prerequisites:
	- Maven
	- Java 11
	- npm
	- docker and docker-compose

## Application setup and start

 1. Clone project
 2. In root folder, run `mvn clean install -DskipTests`
 3. Upon successful maven build, run command `docker-compose up -d`
 4. Application startup takes approximately 2 minutes, but this may vary on different devices.
 5. Quiz-hub page will be available at `http://localhost:3000`

In rare occasions, page may take a little bit longer to load after start. 

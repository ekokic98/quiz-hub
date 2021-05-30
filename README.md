# QuizHub

## How To Use :wrench:

To clone and run this application, you will need [Git](https://git-scm.com), [Java](https://www.oracle.com/java/technologies/javase-downloads.html),
[Maven](https://maven.apache.org/download.cgi), [PostgreSQL](https://www.postgresql.org/download/), [Node.js](https://nodejs.org/en/download) and [Docker](https://www.docker.com/products/docker-desktop).

```bash
# Clone this repository
$ git clone https://github.com/kkadusic/auction-app

# Go into the root directory
$ cd auction-app

# Build the project without running unit tests
$ mvn clean install -DskipTests

# Upon successful maven build, launch the services
$ docker-compose up -d
```

Application startup takes about 2 minutes, but this may vary on different devices. <br>
On rare occasions, a page may take a bit longer to load after the start. <br>
- QuizHub page will be available at [`http://localhost:3000`](http://localhost:3000).

## Contributors

<a href="https://github.com/Lino2007" target="_blank"><img width="100px" height="100px" src="https://github.com/Lino2007.png"></a>
<a href="https://github.com/kkadusic" target="_blank"><img width="100px" height="100px" src="https://github.com/kkadusic.png"></a>
<a href="https://github.com/amra-music" target="_blank"><img width="100px" height="100px" src="https://github.com/amra-music.png"></a>
<a href="https://github.com/fpoljcic" target="_blank"><img width="100px" height="100px" src="https://github.com/fpoljcic.png"></a>

# ROCate
An indoors localization project that aims to locate the users on a college campus, utilizing trilateration and iBeacon protocol

## Classes

- Coordinates2D: A class for storing x,y coordinates on a plane and helpful methods regarding 2D Geometry.
- Beacon: Used in the JSON deserialization process. Represents one beacon seen by a client device.
- JSONReceiverServlet: Receives HTTP POST requests from the client app (yet to be written), deserializes them, and will (eventually) return the client's position to them.


## Development Environment Setup
NOTE: Make sure to do both the "IDE Setup" and the "Testing Environment Setup" steps.

### IDE Setup
Choose your preferred IDE and follow the steps below. If you use a different IDE, you should find out how to import a Gradle project into it.
#### Eclipse
Tested in Eclipse Oxygen.3 (4.7.3) - latest version at time of writing

1. Clone files from Git repository using terminal
1. Open Eclipse Marketplace (under help menu on macOS), search "buildship", and install if not already installed.
1. In eclipse, click File, Import, then select Gradle, Existing Gradle Project
1. Click Next at the wizard
1. Click browse, navigate to the root folder of the repo you cloned, hit open
1. Click Finish.

*Note:* For older versions of Eclipse, you would need to start a new Gradle project, empty the folder, pull from here, then run `./gradlew eclipse`.

Now you have the project properly imported, set up, and you can run the tests.


#### IntelliJ IDEA
Tested in IDEA Ultimate 2018.1 - latest version at time of writing. You can get Ultimate for free as a student. These instructions should also work for Community Edition (free to all).

1. Clone files from Git repository using terminal
1. In IDEA, click File, Open, then navigate to root folder of cloned repo and hit open
1. A little notification box will pop up in the bottom right asking if you want to import gradle project. Select Import Gradle Project.
1. Check "use auto-import"
1. Click ok

Now you have the project properly imported, set up, and you can run the tests.

### Testing Environment Setup
1. Install [Docker](https://www.docker.com/community-edition#/download). If you are a windows user and don't have Windows 10 Pro, you
will need to install [Docker Toolbox](https://docs.docker.com/toolbox/toolbox_install_windows/).
**NOTE:** The docker stuff has not been tested with Docker Toolbox.
2. Technically, you're done now. Some notes:
- You can start a Docker container running the server app by running
`./gradlew startDockerContainer` (once Docker is running on your computer - you should just leave it running during work sessions). It will bind to port 8888. So you can go to `http://localhost:8888/rocate` in a web browser to interact with it.
- You can stop the container with `./gradlew stopDockerContainer`. 
- You can try adding a run configuration in your IDE of choice
if you don't want to always be running these commands from the terminal.
- Don't run any of the other gradle tasks, *especially* those whose names start with `internal` - this will cause issues 

#### Note
You only have to do this if you don't want to mess with installing Tomcat on your local machine.
If you already have Tomcat installed or for some other reason don't want to use Docker, you can use `./gradlew war` to get a .war file (will be located in `build/libs`) and
just put that in the proper directory for your Tomcat install.

## Trilateration Algorithm
Since the development of the Trilateration algorithm will require MTH 165, we will use this [library](https://github.com/lemmingapex/trilateration) instead. This is already configured for you in the gradle project.

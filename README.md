# ROCate
An indoors localization project that aims to locate the users on a college campus, utilizing trilateration and iBeacon protocol

## Classes

- Coordinates2D: A class for storing x,y coordinates on a plane and helpful methods regarding 2D Geometry


## Development Environment Setup

### Eclipse
Tested in Eclipse Oxygen.3 (4.7.3) - latest version at time of writing

1. Clone files from Git repository using terminal
1. Open Eclipse Marketplace (under help menu on macOS), search "buildship", and install if not already installed.
1. In eclipse, click File, Import, then select Gradle, Existing Gradle Project
1. Click Next at the wizard
1. Click browse, navigate to the root folder of the repo you cloned, hit open
1. Click Finish.


Now you have the project properly imported, set up, and you can run the tests.


### IntelliJ IDEA
Tested in IDEA Ultimate 2018.1 - latest version at time of writing. You can get Ultimate for free as a student. These instructions should also work for Community Edition (free to all).

1. Clone files from Git repository using terminal
1. In IDEA, click File, Open, then navigate to root folder of cloned repo and hit open
1. A little notification box will pop up in the bottom right asking if you want to import gradle project. Select Import Gradle Project.
1. Check "use auto-import"
1. Click ok

Now you have the project properly imported, set up, and you can run the tests.

## Trilateration Algorithm
Since the development of the Trilateration algorithm will require MTH 165, we will use this [library](https://github.com/lemmingapex/trilateration) instead. This is already configured for you in the gradle project.

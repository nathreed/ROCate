# ROCate
An indoors localization project that aims to locate the users on a college campus, utilizing trilateration and iBeacon protocol

## Classes

- Coordinates2D: A class for storing x,y coordinates on a plane and helpful methods regarding 2D Geometry


## Development Environment Setup

### Eclipse
Tested in Eclipse Oxygen.2 (4.7.2) - latest version at time of writing

1. Clone files from Git repository using terminal
1. Create new project in Eclipse
1. Name ROCate
1. Uncheck "use default location"
1. Select root directory of project on your filesystem (the directory containing README.md and src/)
1. Hit finish
1. Open src/Coordinates2DTest.java
1. Hover over first error (line 1)
1. Select 3rd option (fix project setup)
1. Hit OK in dialog box

Now you have the project properly imported, set up, and you can run the tests.


### IntelliJ IDEA
Tested in IDEA Ultimate 2017.3 - latest version at time of writing. You can get Ultimate for free as a student. These instructions should also work for Community Edition (free to all).


1. Clone files from Git repository using terminal
1. Open IDEA, hit File > Open
1. Navigate to the ROCate directory you cloned
1. Open src/Coordinates2DTest.java
1. Select "junit" on line 1 (will be highlighted red because of error)
1. Activate contextual menu (option-Enter on Mac)
1. Select "Add JUnit5.0 to classpath"
1. Hit OK

Now you have the project properly imported, set up, and you can run the tests. You may get some warnings thrown when you run the tests, but they do run and you can see the results in the test GUI.

## Trilateration Algorithm
Since the development of the Trilateration algorithm will require MTH 165, [here is an algorithm](https://github.com/lemmingapex/trilateration)

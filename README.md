# UCSB-RPG
CS48 Project

# Compiling

This project should be compiled from intellij. After unzipping this project, construct a new intellij project on "UCSB-RPG/" folder.

Once the project is constructed:
- Right click "UCSB-RPG/src/" > Mark Directory as > Sources Root. 
- Open "File > Project Structure > Project Settings > Project"
	- select a SDK if none is selected
	- set a directory as the output directory.
- Open RunGame.java and click the green triangle to the left of main() fuction to run the game and produce a default run configuration.

the resource file is under "UCSB-RPG/src/resource/".

# Bugs

The file reading code we used to have does not work when packaged as a jar, and often breaks when trasported to other systems. We are able to mitigate this problem by using the nio classes provided in Java 7, But we haven't thoroughly tested it on all system. The menu background image is loaded with awt MediaTracker, so it also does not work as a jar.
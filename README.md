# Boggle-Solver
Project: Boggle Game
	This project allows the users to input their own 4X4 boggle board in command-line and then a list of valid words in the board displayed to the user
         
Technologies: Java, Maven
	Complete project is created using Java 1.8 and Maven 3.6

Implementation Details:
	Main package is com.game.boggle which holds all classes used within this project. I have created various different classes based on the purpose to make it easy to read, understand, navigate, scale and maintain and mainly it gives modularity to application. I have tried to apply as much as possible the various best practices of coding and designing, some of which are listed below,
			1. Single responsibility principle
			2. Commenting & Documentation
			3. Consistent Indentation
			4. Code Grouping
			5. Consistent Naming Scheme
			6. DRY Principle
			7. Avoid Deep Nesting
			8. Limit Line Length by formatting the code
			10. Consistent Temporary Names

Future Scope:
* Create a web app 
* Allow variable size boggle board

How to run: 
	## Pre-requisites for executing the application
* There should be Java 1.8 or later installed on your machine.
* To have maven 3.6+ installed on the machine.
* For windows one can follow this for setup: https://mkyong.com/maven/how-to-install-maven-in-windows/
* For Mac OS one can follow this for setup: https://mkyong.com/maven/install-maven-on-mac-osx/

	## Executing the application
	On any Operating System do the following:
* If running from IDE, you can right click on Boggle.java and select run as java application. 
* If running through command prompt then in command line go to the project folder and then run "mvn clean install" command. once successful, run "java -jar target/boggle-0.0.1-SNAPSHOT.jar" command.

References:
1. https://en.wikipedia.org/wiki/Boggle
2. https://stackoverflow.com/questions/746082/how-to-find-list-of-possible-words-from-a-letter-matrix-boggle-solver%20)
3. http://www.robertgamble.net/2016/01/a-programmers-analysis-of-boggle.html
4. https://docs.oracle.com/javase/tutorial/collections/interfaces/examples/dictionary.txt
5. https://www.puzzle-words.com/boggle-4x4/
6. https://github.com/rgamble/WGS/blob/master/doc/WGS%20Manual.pdf
7. https://mkyong.com/java/java-read-a-file-from-resources-folder/


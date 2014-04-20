##Quiz
====

Quiz project - PiJ Coursework 3


###Instructions
===========
(For Mac)
* Clone the repositry
* Using the command line, go to the cloned folder 'Quiz'

####To start the Server
-------------------
* Enter 'cd QuizServer/src/main/'
* Enter 'javac launcher/QuizServerLauncher.java'
* Enter 'java -Djava.security.policy=Server.java.policy launcher/QuizServerLauncher'
* You should see "Server Up!"

####To start the Set Up Client
--------------------------
* Enter 'javac -cp QuizSetUpClient/src/main/ QuizSetUpClient/src/main/launcher/QuizSetUpClientLauncher.java'
* Enter 'java -cp QuizSetUpClient/src/main/:QuizServer/src/main/ -Djava.security.policy=Client.java.policy launcher/QuizSetUpClientLauncher'
* A Menu should appear

####To start the Play Client
------------------------
* Enter 'javac -cp QuizPlayClient/src/main/ QuizPlayClient/src/main/launcher/QuizPlayClientLauncher.java'
* Enter 'java -cp QuizPlayClient/src/main/:QuizServer/src/main/ -Djava.security.policy=Client.java.policy launcher/QuizPlayClientLauncher'
* A Menu should appear

etutor
======
This project provides numerous demos (most JSF for now) for educational purposes. 
Builds and dependency management are handled by Maven. Many component libraries 
that are used have been updated since the creation of this project. If creating 
your own project based on these samples, you should use the newer libraries and 
follow all migration instructions. Primefaces has a migration guide which is 
available at: http://code.google.com/p/primefaces/wiki/MigrationGuide.

To run this project you will need to copy of "etutor-app" folder, which is
located in the project folder under src/main, to the root of your hard drive.
Next, open the "jdbc.config.properties" file and edit the username and 
password for your installation of MySql database. Finally, restore the
database by using MySql Workbench to import the sql backup files provided
under src/sql.

Frontend 
========
Below instructions are only applicable if you want to change the code. Under the frontend folder, assuming it is Windows environment, 
and "npm" is installed.

> npm -g install grunt-cli karma bower
> npm install
> bower install
> grunt watch

The compiled website is under "build" folder.


Backend
=======
1. This is a GAE "Web Application Project"
2. Make sure you have all the required libraries listed in "required_lib.txt" under WEB-INF\lib
3. Use Eclipse to open the project.
4. Right click on the project, choose "Google", and then "Deploy to App Engine"

Note
----
1. Make sure Google Plugin for Eclipse is installed.
2. You may need to change the application id in appengine-web.xml. You don't have my Google credentials.
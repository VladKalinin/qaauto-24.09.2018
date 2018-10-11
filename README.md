# Automation Framework setup guide (for Mac)

## Download the following components:

1. Go to [Jetbrains official site(IntelliJ IDEA)](https://www.jetbrains.com/idea/download/#section=mac) and download IntelliJ IDEA -
Community version(It is enough for even commerce activity).

2. Go to [Oracle official site(JDK)](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and download JDK package:

* Click on JDK Download button (latest version is enough)

* Accept License Agreement and download JDK package

3. Download [FireFox Browser](https://www.mozilla.org/ru/firefox/new/)

4. Dowload [Geckodriver](https://github.com/mozilla/geckodriver/releases) (Latest version is needed)

## Install the following components:

1. JDK package

2. IntelliJ IDEA application

3. FireFox Browser

## Launch guide

1. Launch IntelliJ IDEA, create New Project, Maven project, choose JDK installed version

2. Open [Maven Repository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.11.0), and click on
Maven tab and copy internal text (it is needed for Selenium setup)

3. Paste copied text to pom.xml file

4. Go to IntelliJ IDEA/View/Check the following: Tool Buttons

5. Press on Maven Project tool bar in the right side and double click on Install item (Selenium Components ought to be installed)

### NOTE

Install geckodriver for proper Selenium work

Use the Terminal:

* echo $PATH
/usr/local/bin/
* mv /Users/Sergii/Downloads/geckodriver /usr/local/bin/geckodriver
---------------------------------------------------------------------

* $ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

* $ brew install geckodriver
* append to your bash config file (e.g., `~/.bashrc`)
* export PATH=$PATH:/path/to/geckodriver
=======
# qaauto-24.09.2018
QA Start Up cources
>>>>>>> 63f5c92aa5b6403290aa24dfab854c8d9a11504d


Some information regarding two files:
pom.xml - Stores information regarding your project/test and your dependencies
.gitignore - file, which stores the folders/file extension which should NOT be pulled to GitHub


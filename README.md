


<br />
<div align="center">

<h3 align="center">CRON Parser</h3>

  <p align="center">
    A simple command line tool to parse Cron Expressions!
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
     </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is a simple Command Line based Cron Parser that resolves Cron expressions in a human readable form.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

This minimal project is purely built on Java.

[Java](https://www.java.com/en/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This project requires Java to be installed and classpath to be set properly, and there are no other requirements for this to run.

### Prerequisites

Follow the below URL to setup Java on the OS of your choice.

[Java Installation](https://www.java.com/en/download/help/download_options.html)


<!-- USAGE EXAMPLES -->
## Usage

## Build

To compile/build this simple command line tool, enter the below commands without any modifications from the project root directory.

```console
cd src/main/java

javac org/deliveroo/CronParser.java

```

## Run

To run this simple command line tool, enter the below command with the cron expression to be evaluated from the project root directory.

```console

java org.deliveroo.CronParser <CRON_EXPRESSION>

```

For example,

```console

java org.deliveroo.CronParser */15 0-23 1,15 \* \* /usr/bin/find

```

This should give you the below output:

```console
minute        0 15 30 45 
hour          0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 
day of month  1 15 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   0 1 2 3 4 5 6 
command       /usr/bin/find
```

<!-- TROUBLESHOOTING -->
## Troubleshooting

1. The tool doesn't give proper output when wild character (*) is being used
A. In some shells, like the zshell, zshell tries to replace the wild character(*) with the directory names, to resolve this, try the below steps.

```console

setopt +o nomatch

```

Using Escape character (\) before the wild character (*), so instead of 

```
*/15 0 1,15 * 1-5 /usr/bin/find
```

Try using

```
*/15 0 1,15 \* 1-5 /usr/bin/find
```

## Testing

To run the included test cases, from the root project directory, run the following command.

```console

./gradlew clean build

```
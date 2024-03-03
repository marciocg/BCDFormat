# BCDFormat for Java

Simple library that parses byte[] array of BCD (Binary-coded decimal) into Strings, and vice-versa.

## Getting Started

### Dependencies

* Just the standard library of any version of Java

### Installing

* Clone the repo
```
git clone https://github.com/marciocg/BCDFormat.git
```
* Generate the jar package with maven
```
./mvnw package
```
* Import the jar to your project

### Usage
> reading the test cases are enough to learn how to encode BCD byte[] array data from Strings, and vice-versa


## Documentation

* Generate the maven site with javadoc and JaCoCo report under ./target/site directory
```
./mvnw clean jacoco:prepare-agent install jacoco:report site
```

## License

This project is licensed under the Apache License 2.0 - see the LICENSE.md file for details

## Acknowledgments

References:
* [Binary-coded decimal - Wikipedia](https://en.wikipedia.org/wiki/Binary-coded_decimal)
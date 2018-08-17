# wordEater
wordEater is a command line tool that reads text and generates a summary of word occurrences, sorted by length (from shorter word to longer) then ASCII value (ascending).

## Build
`cd src`

`javac wordEater/wordEaterMain.java -d ../bin`

## Execute
`cd bin`

`java wordEater/wordEaterMain < theText.txt`

or

`java wordEater/wordEaterMain theText.txt`

## Using Eclipse
The project has been developed using Eclipse Photon 4.8.0.
Unit tests requires JUnit5.

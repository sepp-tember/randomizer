randomizer
==========

This project is still under heavy development, so please don't get too angry when things change a lot. We will do our best to avoid too 
many major changes of the architecture in the future.

`randomizer` is a Java library meant to provide randomized lists, by accessing these lists through iterators that retrieve the elements 
in a randomized order.

Further Information can be found at [http://dev.sepp-tember.net/randomizer/](http://dev.sepp-tember.net/randomizer/)

Retrieval
---------

This libraries release artifacts can be retrieved from a maven repository at
[http://maven.sepp-tember.net/releases/](http://maven.sepp-tember.net/releases/).

An example maven configuration would be:
```xml
	<project xmlns="http://maven.apache.org/POM/4.0.0"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
			https://maven.apache.org/xsd/maven-4.0.0.xsd">
		...
		<dependencies>
			<dependency>
				<groupId>net.sepp-tember.lib</groupId>
				<artifactId>randomizer</artifactId>
				<version>1.0.0</version>
			</dependency>
		</dependencies>
		...
		<repositories>
			<repository>
				<releases>
					<enabled>true</enabled>
				</releases>
				<id>sepp-tember-snapshots</id>
				<url>http://maven.sepp-tember.net/releases/</url>
			</repository>
		</repositories>
		...
	</project>
```

# uConomy

User-driven economy for Bukkit written in Java.

http://dev.bukkit.org/bukkit-plugins/uconomy/

Development builds are available at http://ci.brysonm.com/job/uConomy.

## Contributing

In order to contribute, you must fork and submit pull requests. Pull requests will be accepted if:

1. The author uses similar coding etiquette to the original author in the repository.

2. The pull request is a needed fix or implements a useful feature.

3. The author indents and spaces the same as the original author.

## API

Currently, uConomy does not have any implemented API features for developers, but at some point it probably will.

Below is a tutorial on how to use uConomy's Maven repository to implement the jar file.

```
<repositories>
...
  <repository>
    <id>brysonm-repo</id>
    <url>repo.brysonm.com</url>
  </repository>
...
</repositories>

<dependencies>
...
  <dependency>
    <groupId>com.brysonm.uconomy</groupId>
    <artifactId>uConomy</artifactId>
    <version>LATEST</version>
  <dependency>
...
</dependencies>
```

Apache Flink Starter
===================
A starting point for an [Apache Flink](https://ci.apache.org/projects/flink/flink-docs-master/) with a
multi-node Kafka cluster and a Flink job cluster all under Docker.



## Pre-Requisites

1. Docker

    + [Mac](https://download.docker.com/mac/stable/Docker.dmg)

1. [Gradle](https://gradle.org) - You have a few options here
    + If you're using Intellij, just make sure it's enabled.
    + Run `brew install gradle`

## Up & Running

To get going run,

```
git clone git@github.com:aedenj/apache-flink-starter.git ~/projects/apache-flink-starter
cd ~/projects/apache-flink-starter;./gradlew build;docker-compose up
```

Now you have a multi-node Kafka cluster and Flink job cluster. See the [Kafka cluster repo](https://github.com/aedenj/kafka-cluster-starter) for its operating details.

## Running Your App

There are a couple of ways of running your job depending on what you're trying to accomplish.


### Locally

For quick feedback it's easiest to run the locally,

1. If you're using Intellij, use the usual methods.
1. On the command line run `./gradlew shadowJar run`

### Using the Job Cluster

Run `./gradlew shadowJar startJob`. This will run the job under the job cluster in `docker-compose.yml`.


## Live Reload

On the command line run `./gradlew -t shadowJar startJob`. This approach attempts to simulate live reload using Gradle's `-t` flag by restarting the containers of the Flink job cluster in `docker-compose.yml`.

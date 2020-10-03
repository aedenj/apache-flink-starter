Apache Flink Starter
===================
A starting point for an [Apache Flink](https://ci.apache.org/projects/flink/flink-docs-master/) with a
Kafka cluster and a Flink job cluster all under Docker .

## Pre-Requisites

1. Docker

    + [Mac](https://download.docker.com/mac/stable/Docker.dmg)

1. [Gradle](https://gradle.org) - You have a few options here
    + If you're using Intellij, just make sure it's enabled.
    + Run `brew install gradle`

## Up & Running

With Docker installed, we will need a few external docker networks to run the various containers on.
Run `docker network create flink-net` and `docker network create kafka-net`. If you recieve an already exists message for either of these commands you're good to go.

Now let's clone the repo and fire up our system,

```
git clone git@github.com:aedenj/apache-flink-starter.git ~/projects/apache-flink-starter
cd ~/projects/apache-flink-starter;./gradlew build; docker-compose up
```

Now you have a single node Kafka cluster with various admin tools to make life a little easier. See the [Kafka cluster repo](https://github.com/aedenj/kafka-cluster-starter) for its operating details.

## Running Your App

There are a couple of ways of running your job depending on what you're trying to accomplish.

First, let's setup the kafka topics needed by the Flink job. Assumming you setup the Run,

```
kafkacreatetopic broker-1:19092 1 1 source;
kafkacreatetopic broker-1:19092 1 1 destination;
```

### Locally

For quick feedback it's easiest to run the locally,

1. If you're using Intellij, use the usual methods.
1. On the command line run `./gradlew shadowJar run`

### Using the Job Cluster

Run `./gradlew shadowJar startJob`. This will run the job under the job cluster in `flink-job-cluster.yml`.


## Observing th Job in Action

After starting the job with one of the methods above, let's observe it reading an writing a message from one Kafak topic to another.

1. Start the job using one of the methods above.
1. In a new terminal window start a Kafka producer by running,
```
kafkad kafka-console-producer.sh --broker-list broker-1:19092 --topic source --property "parse.key=true" --property "key.separator=:"
```
1. Enter the message `1:Captain Marvel`
1. Navigate to the [Kafka Topics UI](http://localhost:9002/#/) and inspect both the `source` and `destination` topics.

You should see the message `1:Captain Marvel` in both topics.


## Live Reload

Live reload is a great feature to have in your development loop because it can save you time. The closest I've come to on this is the command `./gradlew -t shadowJar startJob`. This approach attempts to simulate live reload using Gradle's `-t` flag by restarting the containers of the Flink job cluster in `flink-job-cluster.yml`. If you have found a better way, please drop me an email.

Apache Flink Starter
===================
A starting point for an [Apache Flink](https://ci.apache.org/projects/flink/flink-docs-master/) with a
multi-node Kafka cluster and a Flink session cluster all under Docker.



## Pre-Requisites

1. Docker

    + [Mac](https://download.docker.com/mac/stable/Docker.dmg)

1. [Gradle] - You have a few options here
    + If you're using Intellij, just make sure it's enabled.
    + Run `brew install gradle`

## Up & Running

To get going run,

```
git clone git@github.com:aedenj/apache-flink-starter.git ~/projects/apache-flink-starter
cd ~/projects/apache-flink-starter;docker-compose up
```

Now you have a multi-node Kafka cluster and Flink session cluster.

## Running Your App

There are a couple of ways of running your job depending on what you're trying to accomplish.


### Locally

For quick feedback it's easiest to run the locally,

1. If you're using Intellij, use the usual methods.
1. On the command line run ./gradlew -t shadowJar run. [TODO: Live Reload]

### Use the Cluster UI

1. [Naviate](http://localhost:8081/#/submit) to the Flink UI
1. Click on the `+ Add` button in the upper righthand corner and upload it.
1. Once the job has appeared click on it and hit submit.
1. Go check on your job on the [Running Jobs](http://localhost:8081/#/jobs/running) page.

### Flink CLI
You can submit your job to the session cluster using the [Flink CLI](https://ci.apache.org/projects/flink/flink-docs-stable/ops/cli.html). There's a
fews ways of accomplishing that.

1. Run `./gradlew submitJob`
1. `docker run --rm -v "$PWD":/app -w /app --network kafka-net flink:latest flink run -d -m jobmanager:8081 ./build/libs/flink-stream-job-1.0-all.jar


We can make option two a little easier with an alias

```
alias flinkd='docker run --rm -v "$PWD":/app -w /app --network kafka-net flink:latest flink'
```

Refresh your terminal and just run `flinkd run -d -m jobmanager:8081 ./build/libs/flink-stream-job-1.0-all.jar


Apache Flink Starter
===================
A starting point for an [Apache Flink](https://ci.apache.org/projects/flink/flink-docs-master/) project that powers a data pipeline involving Kafka and the ELK stack. There are also various administrative tools like Kafdrop. All these systems are able to run under docker.

## Pre-Requisites

1. Docker on [Mac](https://download.docker.com/mac/stable/Docker.dmg)

1. [Gradle](https://gradle.org) - You have a few options here
    + If you're using Intellij, just make sure it's enabled.
    + Run `brew install gradle`

## Up & Running

Let's first clone the repo and fire up our system,

```
git clone git@github.com:aedenj/apache-flink-starter.git ~/projects/apache-flink-starter
cd ~/projects/apache-flink-starter;./gradlew kafkaUp
```
Now you have a single node Kafka cluster with various admin tools to make life a little easier. See the [Kafka cluster repo](https://github.com/aedenj/kafka-cluster-starter) for its operating details.

## Running the App

The sample job in this repo reads from a topic named `source` and writes to a topic named `destination`.
There are a couple of ways of running this job depending on what you're trying to accomplish.

First, let's setup the kafka topics. Run `./gradlew createTopics`.

### Locally

For quick feedback it's easiest to run the job locally,

1. If you're using Intellij, use the usual methods.
1. On the command line run `./gradlew shadowJar run`

### Using the Job Cluster

Run `./gradlew shadowJar startJob`. This will run the job within a job cluster that is setup in `flink-job-cluster.yml`. That cluster will run against the Kafka cluster started earlier.


### Observing the Job in Action

After starting the job with one of the methods above, let's observe it reading an writing a message from one Kafak topic to another.

1. Start the job using one of the methods above.
1. In a new terminal start a Kafka producer by running `./scripts/start-kafka-producer.sh`
1. You'll see the prompt `>`. Enter the message `1:{ message: "Hello World!" }`
1. Navigate to the [Kafdrop](http://localhost:8001/#/) and view the messages both the `source` and `destination` topics. Be sure to change format to default or else you will not see any messages.

You should see the message `1:{ message: "Hello World!" }` in both topics.


### Live Reload

Live reload is a great feature to have in your development loop as it can save you time. The closest I've come to on this is the command `./gradlew -t shadowJar startJob`. This approach attempts to simulate live reload using Gradle's `-t` flag by restarting the containers of the Flink job cluster in `flink-job-cluster.yml`.

Another approach I've explored is using [JRebel](https://manuals.jrebel.com/jrebel/standalone/index.html), which can make classes reloadable with existing class loaders. Only changed classes are recompiled and instantly reloaded in the running application. JRebel will not re-run `main` for you so I've had mixed results with its effectiveness.

If you've found a better way, please drop me an email.

## Grafana, Elastic Search and Logstash

This repo also comes with the ability to spin up Grafana, Elastic Search and Logstash that let's you try out the common use case of using Grafana as a visual tool for querying data from Elastic Search. Simply run `./gradlew grafanaUp`. There are additional containers present to support administractive tasks,

1. [Dejavu](https://github.com/appbaseio/dejavu) - Dejavu is a UI for browsing data in Elasticsearch..
1. [Cerebro](https://github.com/lmenezes/cerebro) - Is a cluster management UI for Elastic Search

Some things to note about the setup,

1. Elasticsearch as already been setup as a [datasource](https://github.com/aedenj/apache-flink-starter/tree/master/conf/grafana/provisioning/datasources) for Grafana.
1. Logstash has a [basic configuration](https://github.com/aedenj/apache-flink-starter/tree/master/conf/logstash) to read from the Kafka cluster and write to Elasticsearch.
1. No dashboard has been setup in Grafana.

### Viewing Data with Dejavu

1. Run `./gradlew grafanaUp` if you haven't already.
1. [Open Dejavu](http://localhost:1358/?appname=&url=&mode=edit)
1. Enter `http://elasticsearch:9200` into the input box with hint text of `URL for Cluster`.
1. Enter `*` in the input box with the hint text of `Appname`

### Managing Elastic Search with Cerebro

1. [Open Cerebro](http://localhost:9004/#/overview?host=http:%2F%2Felasticsearch:9200) and give it a spin. It's feature rich.

###build new image
```bash
mvn compile com.google.cloud.tools:jib-maven-plugin:2.5.0:build
```

###example
```bash
taaraora@dell-xps:~/IdeaProjects/jtt/untitled$ docker run --rm --network host taaraora/jt-checker:latest -c neeeee-jfnsw -h localhost -p 9300
cluster.name is neeeee-jfnsw
hostname is localhost
port is 9300
cluster status:{"cluster_name":"neeeee-jfnsw","status":"green","timed_out":false,"number_of_nodes":2,"number_of_data_nodes":2,"active_primary_shards":1,"active_shards":2,"relocating_shards":0,"initializing_shards":0,"unassigned_shards":0,"delayed_unassigned_shards":0,"number_of_pending_tasks":0,"number_of_in_flight_fetch":0,"task_max_waiting_in_queue_millis":0,"active_shards_percent_as_number":100.0}

```

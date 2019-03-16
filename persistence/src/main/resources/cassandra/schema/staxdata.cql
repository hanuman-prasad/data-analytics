CREATE KEYSPACE IF NOT EXISTS local_entities
WITH replication = {
'class' : 'SimpleStrategy',
'replication_factor' : 1
};

USE local_entities;

CREATE TABLE IF NOT EXISTS staxdata (
key uuid,
payload blob,
PRIMARY KEY (key)
);

CREATE TABLE IF NOT EXISTS entity_index_1 (
entity_class text,
index_name text,
index_value blob,
entity_id text,
key uuid,
PRIMARY KEY ((entity_class, index_name, index_value), entity_id)
);



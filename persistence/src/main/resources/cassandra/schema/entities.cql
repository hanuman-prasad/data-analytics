CREATE KEYSPACE IF NOT EXISTS local_entities
WITH replication = {
'class' : 'SimpleStrategy',
'replication_factor' : 1
};

USE local_entities;

CREATE TABLE IF NOT EXISTS entities (
key uuid,
entity_id text,
payload blob,
PRIMARY KEY (key, entity_id)
);


CREATE TABLE IF NOT EXISTS entities_by_ids (
website text,
entity_class text,
entity_id text,
PRIMARY KEY (website, entity_class, entity_id)
);


CREATE TABLE IF NOT EXISTS entities_model (
website text,
entity_class text,
entity_name text,
entity_id text,
key uuid,
PRIMARY KEY (website, entity_class, entity_name, entity_id)
);


CREATE TABLE IF NOT EXISTS entities_projection_1 (
entity_class text,
index_name text,
index_value text,
entity_id text,
key uuid,
PRIMARY KEY ((entity_class, index_name, index_value), entity_id)
);



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
PRIMARY KEY ((website, entity_class), entity_id)
);


CREATE TABLE IF NOT EXISTS subentities_projection (
website text,
entity_class text,
entity_subclass text,
subentity_value text,
key uuid,
PRIMARY KEY ((website, entity_class, entity_subclass, subentity_value), key)
);

CREATE TABLE IF NOT EXISTS entities_projection_1 (
website text,
entity_class text,
index_name text,
index_value text,
entity_id text,
key uuid,
PRIMARY KEY ((website, entity_class, index_name, index_value), entity_id)
);



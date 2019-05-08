package edu.elearning.service.datacache.cache;

import edu.elearning.cassandra.repository.CassandraRepository;
import edu.elearning.cassandra.repository.Repository;
import edu.elearning.se.AsteriModel;
import edu.elearning.se.UserWebsite;

import java.util.List;
import java.util.Optional;

public class CassandraPersister<K, V> implements CachePersister<K, V> {

    private final UserWebsite website;
    private final Class<AsteriModel> modelClass;

    private final Repository repository = new CassandraRepository();

    public CassandraPersister(UserWebsite website, Class<AsteriModel> modelClass) {
        this.website = website;
        this.modelClass = modelClass;
    }

    @Override
    public void persist(K key, V value) {

    }

    @Override
    public int populate(Cache<K, V> targetCache) {
        int size = targetCache.size();

        List<String> ids = repository.queryIds(website, modelClass);
        if(ids.size() > size) {
            ids = ids.subList(0, size);
        }

        ids.stream()
                .forEach(id -> {
                    Optional<AsteriModel> entityOptional = repository.query(website, modelClass, "id", id).stream().findFirst();
                    if(entityOptional.isPresent()) {
                        AsteriModel entity = entityOptional.get();
//                        targetCache.put(entity.getObjectId(), entity);
                    }
                });


        return ids.size();
    }

    @Override
    public V getValue(K key) {
        return null;
    }
}

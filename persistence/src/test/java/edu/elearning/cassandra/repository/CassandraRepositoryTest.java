package edu.elearning.cassandra.repository;

import com.datastax.driver.core.Session;
import edu.elearning.cassandra.connection.CassandraConnectionFactory;
import edu.elearning.se.User;
import edu.elearning.se.UserWebsite;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class CassandraRepositoryTest {

    private Session session;

    @Before
    public void setUp(){
        session = CassandraConnectionFactory.getSession();
    }
    @Test
    public void save() {

        CassandraRepository repository = new CassandraRepository(session);

        User user = User.builder()
                .id("HARDWARE-1")
                .displayName("carma")
                .upVotes("888")
                .views("6598")
                .creationDate(LocalDateTime.now())
                .lastAccessDate(LocalDateTime.now())
                .userWebsite(UserWebsite.HARDWARE)
                .build();

        repository.save(user);

    }
}
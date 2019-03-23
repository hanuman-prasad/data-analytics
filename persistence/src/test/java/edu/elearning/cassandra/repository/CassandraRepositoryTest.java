package edu.elearning.cassandra.repository;

import com.google.common.collect.ImmutableList;
import edu.elearning.se.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CassandraRepositoryTest {

    private static Repository repository;

    @BeforeClass
    public static void setUp(){
        repository = new CassandraRepository();
    }

    @Test
    public void save_user_then_query() {

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

        List<AsteriModel> persistedUser = repository.query(User.class, "id", "HARDWARE-1");

        assertNotNull(persistedUser);
        assertEquals("888", ((User)persistedUser.get(0)).getUpVotes());

    }

    @Test
    public void save_post_then_query() {

        Post post = Post.builder()
                .id("1")
                .body("Testing post")
                .creationDate(LocalDateTime.now())
                .postType(PostType.ANSWER)
                .title("Test")
                .tags(ImmutableList.of("wiki", "para", "iopwiero"))
                .userWebsite(UserWebsite.STACKOVERFLOW)
                .build();

        Post post1 = Post.builder()
                .id("2")
                .body("Another post")
                .creationDate(LocalDateTime.now())
                .postType(PostType.ANSWER)
                .title("Multiple post")
                .tags(ImmutableList.of("se", "iopwiero", "medium"))
                .userWebsite(UserWebsite.STACKOVERFLOW)
                .build();

        repository.save(post);
        repository.save(post1);

        Post queryPostById = (Post) query(Post.class, "id", "1");
        assertEquals("1", queryPostById.getId());
        assertEquals("Test", queryPostById.getTitle());

        List<AsteriModel> queryPostByTag =  queryAll(Post.class, "tags", "iopwiero");
        assertTrue(queryPostByTag.size() == 2);


    }


    private static AsteriModel query(Class<? extends AsteriModel> kClass, String name, String value) {
        return repository.query(kClass, name, value).stream().findFirst().get();
    }

    private static List<AsteriModel> queryAll(Class<? extends AsteriModel> kClass, String name, String value) {
        return repository.query(kClass, name, value).stream().collect(toList());
    }
}
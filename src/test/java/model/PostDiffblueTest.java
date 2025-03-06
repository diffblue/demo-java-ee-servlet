package model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Post#Post()}
   *   <li>{@link Post#setBody(String)}
   *   <li>{@link Post#setPost_id(int)}
   *   <li>{@link Post#setPost_time(String)}
   *   <li>{@link Post#setUser_id(int)}
   *   <li>{@link Post#getBody()}
   *   <li>{@link Post#getPost_id()}
   *   <li>{@link Post#getPost_time()}
   *   <li>{@link Post#getUser_id()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void Post.<init>()", "void Post.<init>(int, int, String, String)", "String Post.getBody()",
      "int Post.getPost_id()", "String Post.getPost_time()", "int Post.getUser_id()", "void Post.setBody(String)",
      "void Post.setPost_id(int)", "void Post.setPost_time(String)", "void Post.setUser_id(int)"})
  public void testGettersAndSetters() {
    // Arrange and Act
    Post actualPost = new Post();
    actualPost.setBody("Not all who wander are lost");
    actualPost.setPost_id(1);
    actualPost.setPost_time("Post time");
    actualPost.setUser_id(1);
    String actualBody = actualPost.getBody();
    int actualPost_id = actualPost.getPost_id();
    String actualPost_time = actualPost.getPost_time();

    // Assert
    assertEquals("Not all who wander are lost", actualBody);
    assertEquals("Post time", actualPost_time);
    assertEquals(1, actualPost_id);
    assertEquals(1, actualPost.getUser_id());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Post#Post(int, int, String, String)}
   *   <li>{@link Post#setBody(String)}
   *   <li>{@link Post#setPost_id(int)}
   *   <li>{@link Post#setPost_time(String)}
   *   <li>{@link Post#setUser_id(int)}
   *   <li>{@link Post#getBody()}
   *   <li>{@link Post#getPost_id()}
   *   <li>{@link Post#getPost_time()}
   *   <li>{@link Post#getUser_id()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void Post.<init>()", "void Post.<init>(int, int, String, String)", "String Post.getBody()",
      "int Post.getPost_id()", "String Post.getPost_time()", "int Post.getUser_id()", "void Post.setBody(String)",
      "void Post.setPost_id(int)", "void Post.setPost_time(String)", "void Post.setUser_id(int)"})
  public void testGettersAndSetters_whenOne() {
    // Arrange and Act
    Post actualPost = new Post(1, 1, "Not all who wander are lost", "Post time");
    actualPost.setBody("Not all who wander are lost");
    actualPost.setPost_id(1);
    actualPost.setPost_time("Post time");
    actualPost.setUser_id(1);
    String actualBody = actualPost.getBody();
    int actualPost_id = actualPost.getPost_id();
    String actualPost_time = actualPost.getPost_time();

    // Assert
    assertEquals("Not all who wander are lost", actualBody);
    assertEquals("Post time", actualPost_time);
    assertEquals(1, actualPost_id);
    assertEquals(1, actualPost.getUser_id());
  }
}

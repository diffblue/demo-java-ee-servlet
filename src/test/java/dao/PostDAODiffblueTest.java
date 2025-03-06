package dao;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import model.Post;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostDAODiffblueTest {
  /**
   * Test {@link PostDAO#insertPost(int, String)}.
   * <p>
   * Method under test: {@link PostDAO#insertPost(int, String)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void PostDAO.insertPost(int, String)"})
  public void testInsertPost() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new PostDAO()).insertPost(1, "Not all who wander are lost");
  }

  /**
   * Test {@link PostDAO#getAllPost()}.
   * <p>
   * Method under test: {@link PostDAO#getAllPost()}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.util.ArrayList PostDAO.getAllPost()"})
  public void testGetAllPost() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new PostDAO()).getAllPost();
  }

  /**
   * Test {@link PostDAO#getUserPost(int)}.
   * <p>
   * Method under test: {@link PostDAO#getUserPost(int)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.util.ArrayList PostDAO.getUserPost(int)"})
  public void testGetUserPost() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new PostDAO()).getUserPost(1);
  }

  /**
   * Test {@link PostDAO#deletePost(int)}.
   * <p>
   * Method under test: {@link PostDAO#deletePost(int)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void PostDAO.deletePost(int)"})
  public void testDeletePost() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new PostDAO()).deletePost(1);
  }

  /**
   * Test {@link PostDAO#getPost(int)}.
   * <p>
   * Method under test: {@link PostDAO#getPost(int)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Post PostDAO.getPost(int)"})
  public void testGetPost() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new PostDAO()).getPost(1);
  }

  /**
   * Test {@link PostDAO#updatePost(Post)}.
   * <p>
   * Method under test: {@link PostDAO#updatePost(Post)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String PostDAO.updatePost(Post)"})
  public void testUpdatePost() {
    // Arrange
    PostDAO postDAO = new PostDAO();

    // Act and Assert
    assertEquals("Post Update Failed.", postDAO.updatePost(new Post(1, 1, "Not all who wander are lost", "Post time")));
  }

  /**
   * Test new {@link PostDAO} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link PostDAO}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void PostDAO.<init>()"})
  public void testNewPostDAO() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   There are no fields that could be asserted on.

    // Arrange and Act
    new PostDAO();
  }
}

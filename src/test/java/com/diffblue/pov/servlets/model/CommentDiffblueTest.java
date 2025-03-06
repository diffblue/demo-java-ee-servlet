package com.diffblue.pov.servlets.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.Timestamp;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CommentDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Comment}
   *   <li>{@link Comment#setComment(String)}
   *   <li>{@link Comment#setCommentId(int)}
   *   <li>{@link Comment#setCommentTime(Timestamp)}
   *   <li>{@link Comment#setPostId(int)}
   *   <li>{@link Comment#setUserId(int)}
   *   <li>{@link Comment#toString()}
   *   <li>{@link Comment#getComment()}
   *   <li>{@link Comment#getCommentId()}
   *   <li>{@link Comment#getCommentTime()}
   *   <li>{@link Comment#getPostId()}
   *   <li>{@link Comment#getUserId()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void Comment.<init>()", "String Comment.getComment()", "int Comment.getCommentId()",
      "Timestamp Comment.getCommentTime()", "int Comment.getPostId()", "int Comment.getUserId()",
      "void Comment.setComment(String)", "void Comment.setCommentId(int)", "void Comment.setCommentTime(Timestamp)",
      "void Comment.setPostId(int)", "void Comment.setUserId(int)", "String Comment.toString()"})
  public void testGettersAndSetters() {
    // Arrange and Act
    Comment actualComment = new Comment();
    actualComment.setComment("Comment");
    actualComment.setCommentId(1);
    Timestamp commentTime = mock(Timestamp.class);
    actualComment.setCommentTime(commentTime);
    actualComment.setPostId(1);
    actualComment.setUserId(1);
    actualComment.toString();
    String actualComment2 = actualComment.getComment();
    int actualCommentId = actualComment.getCommentId();
    Timestamp actualCommentTime = actualComment.getCommentTime();
    int actualPostId = actualComment.getPostId();

    // Assert
    assertEquals("Comment", actualComment2);
    assertEquals(1, actualCommentId);
    assertEquals(1, actualPostId);
    assertEquals(1, actualComment.getUserId());
    assertSame(commentTime, actualCommentTime);
  }
}

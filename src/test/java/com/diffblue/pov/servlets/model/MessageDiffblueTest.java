package com.diffblue.pov.servlets.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MessageDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Message#Message()}
   *   <li>{@link Message#setChat_id(int)}
   *   <li>{@link Message#setChat_time(String)}
   *   <li>{@link Message#setFrom_user(String)}
   *   <li>{@link Message#setMessage(String)}
   *   <li>{@link Message#setTo_user(String)}
   *   <li>{@link Message#getChat_id()}
   *   <li>{@link Message#getChat_time()}
   *   <li>{@link Message#getFrom_user()}
   *   <li>{@link Message#getMessage()}
   *   <li>{@link Message#getTo_user()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void Message.<init>()", "void Message.<init>(int, String, String, String, String)",
      "int Message.getChat_id()", "String Message.getChat_time()", "String Message.getFrom_user()",
      "String Message.getMessage()", "String Message.getTo_user()", "void Message.setChat_id(int)",
      "void Message.setChat_time(String)", "void Message.setFrom_user(String)", "void Message.setMessage(String)",
      "void Message.setTo_user(String)"})
  public void testGettersAndSetters() {
    // Arrange and Act
    Message actualMessage = new Message();
    actualMessage.setChat_id(1);
    actualMessage.setChat_time("Chat time");
    actualMessage.setFrom_user("jane.doe@example.org");
    actualMessage.setMessage("Not all who wander are lost");
    actualMessage.setTo_user("To user");
    int actualChat_id = actualMessage.getChat_id();
    String actualChat_time = actualMessage.getChat_time();
    String actualFrom_user = actualMessage.getFrom_user();
    String actualMessage2 = actualMessage.getMessage();

    // Assert
    assertEquals("Chat time", actualChat_time);
    assertEquals("Not all who wander are lost", actualMessage2);
    assertEquals("To user", actualMessage.getTo_user());
    assertEquals("jane.doe@example.org", actualFrom_user);
    assertEquals(1, actualChat_id);
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Message#Message(int, String, String, String, String)}
   *   <li>{@link Message#setChat_id(int)}
   *   <li>{@link Message#setChat_time(String)}
   *   <li>{@link Message#setFrom_user(String)}
   *   <li>{@link Message#setMessage(String)}
   *   <li>{@link Message#setTo_user(String)}
   *   <li>{@link Message#getChat_id()}
   *   <li>{@link Message#getChat_time()}
   *   <li>{@link Message#getFrom_user()}
   *   <li>{@link Message#getMessage()}
   *   <li>{@link Message#getTo_user()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void Message.<init>()", "void Message.<init>(int, String, String, String, String)",
      "int Message.getChat_id()", "String Message.getChat_time()", "String Message.getFrom_user()",
      "String Message.getMessage()", "String Message.getTo_user()", "void Message.setChat_id(int)",
      "void Message.setChat_time(String)", "void Message.setFrom_user(String)", "void Message.setMessage(String)",
      "void Message.setTo_user(String)"})
  public void testGettersAndSetters_whenOne() {
    // Arrange and Act
    Message actualMessage = new Message(1, "jane.doe@example.org", "To user", "Not all who wander are lost",
        "Chat time");
    actualMessage.setChat_id(1);
    actualMessage.setChat_time("Chat time");
    actualMessage.setFrom_user("jane.doe@example.org");
    actualMessage.setMessage("Not all who wander are lost");
    actualMessage.setTo_user("To user");
    int actualChat_id = actualMessage.getChat_id();
    String actualChat_time = actualMessage.getChat_time();
    String actualFrom_user = actualMessage.getFrom_user();
    String actualMessage2 = actualMessage.getMessage();

    // Assert
    assertEquals("Chat time", actualChat_time);
    assertEquals("Not all who wander are lost", actualMessage2);
    assertEquals("To user", actualMessage.getTo_user());
    assertEquals("jane.doe@example.org", actualFrom_user);
    assertEquals(1, actualChat_id);
  }
}

package com.diffblue.pov.servlets.util;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBConnectionDiffblueTest {
  /**
   * Test {@link DBConnection#getInstance()}.
   * <p>
   * Method under test: {@link DBConnection#getInstance()}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"DBConnection DBConnection.getInstance()"})
  public void testGetInstance() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    DBConnection.getInstance();
  }
}

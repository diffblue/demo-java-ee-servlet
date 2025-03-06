package com.diffblue.pov.servlets.controller;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MessageServletDiffblueTest {
  /**
   * Test new {@link MessageServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link MessageServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.<init>()"})
  public void testNewMessageServlet() {
    // Arrange, Act and Assert
    assertNull((new MessageServlet()).getServletConfig());
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletRequestWrapper#HttpServletRequestWrapper(HttpServletRequest)} with request is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_whenHttpServletRequestWrapperWithRequestIsNull() throws IOException, ServletException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.IllegalArgumentException: Request cannot be null
    //       at javax.servlet.ServletRequestWrapper.<init>(ServletRequestWrapper.java:50)
    //       at javax.servlet.http.HttpServletRequestWrapper.<init>(HttpServletRequestWrapper.java:47)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(null);

    // Act
    messageServlet.doGet(request, new HttpServletResponseWrapper(null));
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletRequestWrapper#HttpServletRequestWrapper(HttpServletRequest)} with request is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_whenHttpServletRequestWrapperWithRequestIsNull() throws IOException, ServletException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.IllegalArgumentException: Request cannot be null
    //       at javax.servlet.ServletRequestWrapper.<init>(ServletRequestWrapper.java:50)
    //       at javax.servlet.http.HttpServletRequestWrapper.<init>(HttpServletRequestWrapper.java:47)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(null);

    // Act
    messageServlet.doPost(request, new HttpServletResponseWrapper(null));
  }
}

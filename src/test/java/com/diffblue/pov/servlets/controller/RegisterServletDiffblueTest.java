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

public class RegisterServletDiffblueTest {
  /**
   * Test new {@link RegisterServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RegisterServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.<init>()"})
  public void testNewRegisterServlet() {
    // Arrange, Act and Assert
    assertNull((new RegisterServlet()).getServletConfig());
  }

  /**
   * Test {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletRequestWrapper#HttpServletRequestWrapper(HttpServletRequest)} with request is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doGet(HttpServletRequest, HttpServletResponse)"})
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
    RegisterServlet registerServlet = new RegisterServlet();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(null);

    // Act
    registerServlet.doGet(request, new HttpServletResponseWrapper(null));
  }

  /**
   * Test {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletRequestWrapper#HttpServletRequestWrapper(HttpServletRequest)} with request is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doPost(HttpServletRequest, HttpServletResponse)"})
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
    RegisterServlet registerServlet = new RegisterServlet();
    HttpServletRequestWrapper request = new HttpServletRequestWrapper(null);

    // Act
    registerServlet.doPost(request, new HttpServletResponseWrapper(null));
  }
}

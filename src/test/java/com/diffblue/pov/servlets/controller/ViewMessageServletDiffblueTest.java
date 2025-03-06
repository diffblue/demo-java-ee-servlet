package com.diffblue.pov.servlets.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

public class ViewMessageServletDiffblueTest {
  /**
   * Test new {@link ViewMessageServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ViewMessageServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.<init>()"})
  public void testNewViewMessageServlet() {
    // Arrange, Act and Assert
    assertNull((new ViewMessageServlet()).getServletConfig());
  }

  /**
   * Test {@link ViewMessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link MockHttpSession#MockHttpSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenMockHttpSession() throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setSession(new MockHttpSession());
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    viewMessageServlet.doGet(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof Set);
    assertEquals("login", response.getRedirectedUrl());
    assertEquals(302, response.getStatus());
    assertTrue(headerNames.contains("Location"));
    assertTrue(response.isCommitted());
  }

  /**
   * Test {@link ViewMessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_thenMockHttpServletResponseHeaderNamesSizeIsOne() throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    viewMessageServlet.doGet(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof Set);
    assertEquals("login", response.getRedirectedUrl());
    assertEquals(302, response.getStatus());
    assertTrue(headerNames.contains("Location"));
    assertTrue(response.isCommitted());
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link MockHttpSession#MockHttpSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenMockHttpSession() throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setSession(new MockHttpSession());
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    viewMessageServlet.doPost(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof Set);
    assertEquals("login", response.getRedirectedUrl());
    assertEquals(302, response.getStatus());
    assertTrue(headerNames.contains("Location"));
    assertTrue(response.isCommitted());
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenMockHttpServletResponseHeaderNamesSizeIsOne() throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    viewMessageServlet.doPost(request, response);

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof Set);
    assertEquals("login", response.getRedirectedUrl());
    assertEquals(302, response.getStatus());
    assertTrue(headerNames.contains("Location"));
    assertTrue(response.isCommitted());
  }
}

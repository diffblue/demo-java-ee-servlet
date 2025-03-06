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

public class LogoutServletDiffblueTest {
  /**
   * Test new {@link LogoutServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link LogoutServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.<init>()"})
  public void testNewLogoutServlet() {
    // Arrange, Act and Assert
    assertNull((new LogoutServlet()).getServletConfig());
  }

  /**
   * Test {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link MockHttpSession#MockHttpSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenMockHttpSession() throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setSession(new MockHttpSession());
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    logoutServlet.doGet(request, response);

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
   * Test {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_thenMockHttpServletResponseHeaderNamesSizeIsOne() throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    logoutServlet.doGet(request, response);

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
   * Test {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link MockHttpSession#MockHttpSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenMockHttpSession() throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setSession(new MockHttpSession());
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    logoutServlet.doPost(request, response);

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
   * Test {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenMockHttpServletResponseHeaderNamesSizeIsOne() throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    logoutServlet.doPost(request, response);

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

package controller;

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

public class HomeServletDiffblueTest {
  /**
   * Test new {@link HomeServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link HomeServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.<init>()"})
  public void testNewHomeServlet() {
    // Arrange, Act and Assert
    assertNull((new HomeServlet()).getServletConfig());
  }

  /**
   * Test {@link HomeServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link MockHttpSession#MockHttpSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenMockHttpSession() throws IOException, ServletException {
    // Arrange
    HomeServlet homeServlet = new HomeServlet();

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setSession(new MockHttpSession());
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    homeServlet.doGet(request, response);

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
   * Test {@link HomeServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_thenMockHttpServletResponseHeaderNamesSizeIsOne() throws IOException, ServletException {
    // Arrange
    HomeServlet homeServlet = new HomeServlet();
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    homeServlet.doGet(request, response);

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
   * Test {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link MockHttpSession#MockHttpSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenMockHttpSession() throws IOException, ServletException {
    // Arrange
    HomeServlet homeServlet = new HomeServlet();

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setSession(new MockHttpSession());
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    homeServlet.doPost(request, response);

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
   * Test {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenMockHttpServletResponseHeaderNamesSizeIsOne() throws IOException, ServletException {
    // Arrange
    HomeServlet homeServlet = new HomeServlet();
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    homeServlet.doPost(request, response);

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

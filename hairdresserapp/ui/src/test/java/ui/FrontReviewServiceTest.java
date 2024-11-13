package ui;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class FrontReviewServiceTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockHttpResponse;

    @Mock 
    private HttpResponse<Void> mockVoidResponse;

    @InjectMocks
    private FrontReviewService frontReviewService;

    @Captor
    private ArgumentCaptor<HttpRequest> requestCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialiser FrontReviewService med mock HttpClient og ObjectMapper
        frontReviewService = new FrontReviewService(mockHttpClient);
    }

    @Test
    void testGetReviews_Success() throws IOException, InterruptedException {
        
        String mockResponseBody = "[{\"id\":1,\"review\":\"Great service!\"}]";
        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(mockHttpResponse);
        when(mockHttpResponse.body()).thenReturn(mockResponseBody);

        
        String reviews = frontReviewService.getReviews();

        
        assertEquals(mockResponseBody, reviews);
        verify(mockHttpClient, times(1)).send(requestCaptor.capture(), eq(HttpResponse.BodyHandlers.ofString()));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("http://localhost:8080/api/reviews", capturedRequest.uri().toString());
        assertEquals("GET", capturedRequest.method());
    }

    @Test
    void testGetReviews_IOException() throws IOException, InterruptedException {
       
        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenThrow(new IOException("Network error"));

        
        IOException exception = assertThrows(IOException.class, () -> {
            frontReviewService.getReviews();
        });
        assertEquals("Network error", exception.getMessage());
        verify(mockHttpClient, times(1)).send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString()));
    }

    @Test
    void testAddReview_Success() throws IOException, InterruptedException {
        
        String review = "Excellent service!";
        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.discarding())))
                .thenReturn(mockVoidResponse);

        
        frontReviewService.addReview(review);

        
        verify(mockHttpClient, times(1)).send(requestCaptor.capture(), eq(HttpResponse.BodyHandlers.discarding()));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("http://localhost:8080/api/reviews", capturedRequest.uri().toString());
        assertEquals("POST", capturedRequest.method());
        
    }

    @Test
    void testAddReview_InterruptedException() throws IOException, InterruptedException {
        
        String review = "Good job!";
        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.discarding())))
                .thenThrow(new InterruptedException("Interrupted"));

       
        InterruptedException exception = assertThrows(InterruptedException.class, () -> {
            frontReviewService.addReview(review);
        });
        assertEquals("Interrupted", exception.getMessage());
        verify(mockHttpClient, times(1)).send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.discarding()));
    }
}

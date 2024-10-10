/*
 * package com.acorn.anpa.user.controller;
 * 
 * import static org.junit.Assert.assertNotNull; import static
 * org.mockito.Mockito.*; import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import
 * static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 * 
 * import org.junit.Before; import org.junit.Ignore; import
 * org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.MockitoAnnotations; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.MediaType; import
 * org.springframework.messaging.simp.SimpMessagingTemplate; import
 * org.springframework.test.context.junit.jupiter.SpringJUnitConfig; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.setup.MockMvcBuilders; import
 * org.springframework.web.context.WebApplicationContext;
 * 
 * import com.acorn.anpa.chat.controller.ChatController; import
 * com.acorn.anpa.chat.controller.ChatRequestDto; import
 * com.acorn.anpa.cmn.PLog;
 * 
 * @SpringJUnitConfig public class ChatControllerTest implements PLog{
 * 
 * @Autowired WebApplicationContext webApplicationContext;
 * 
 * MockMvc mockMvc;
 * 
 * @Mock private SimpMessagingTemplate messagingTemplate;
 * 
 * @InjectMocks private ChatController chatController;
 * 
 * @Before public void setUp() { MockitoAnnotations.initMocks(this); //
 * initMocks 사용 }
 * 
 * @Ignore
 * 
 * @Test public void testChatPage() throws Exception {
 * mockMvc.perform(get("/chat/chat.do")) .andExpect(status().isOk())
 * .andExpect(view().name("user/chatPage")); }
 * 
 * @Ignore
 * 
 * @Test public void testEnterMessage() throws Exception { ChatRequestDto dto =
 * new ChatRequestDto(); // 필요한 데이터를 설정하세요 dto.setSender("user1");
 * dto.setContent("Hello!");
 * 
 * mockMvc.perform(post("/chat/enter") .contentType(MediaType.APPLICATION_JSON)
 * .content("{\"sender\":\"user1\",\"content\":\"Hello!\"}"))
 * .andExpect(status().isOk());
 * 
 * verify(messagingTemplate, times(1)).convertAndSend(eq("/sub/chat/room/1"),
 * eq(dto)); }
 * 
 * @Ignore
 * 
 * @Test public void testSendMessage() throws Exception { ChatRequestDto dto =
 * new ChatRequestDto(); // 필요한 데이터를 설정하세요 dto.setSender("user1");
 * dto.setContent("How are you?");
 * 
 * mockMvc.perform(post("/chat/message")
 * .contentType(MediaType.APPLICATION_JSON)
 * .content("{\"sender\":\"user1\",\"content\":\"How are you?\"}"))
 * .andExpect(status().isOk());
 * 
 * verify(messagingTemplate, times(1)).convertAndSend(eq("/sub/chat/room/1"),
 * eq(dto)); }
 * 
 * @Test public void beans() {
 * log.debug("┌──────────────────────────────────────────┐");
 * log.debug("│ beans()                                  │");
 * log.debug("└──────────────────────────────────────────┘");
 * 
 * log.debug("webApplicationContext:" + webApplicationContext);
 * log.debug("mockMvc:" + mockMvc);
 * 
 * assertNotNull(webApplicationContext); assertNotNull(mockMvc); } }
 */
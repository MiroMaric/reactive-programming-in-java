package rs.miromaric.plutus.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rs.miromaric.plutus.user.common.config.PlutusCommonTestConfig;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.user.service.SystemUserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rs.miromaric.plutus.user.data.UserTestData.MIRO;

@WebMvcTest(SystemUserController.class)
@Import(PlutusCommonTestConfig.class)
public class SystemUserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    SystemUserService systemUserService;

    @Test
    public void saveSystemUser() throws Exception {
        when(systemUserService.save(MIRO)).thenReturn(MIRO);
        mockMvc.perform(post("/api/v1/users/")
                                .content(mapper.writeValueAsString(MIRO))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(MIRO), false));
    }


    @Test
    public void getSystemUser() throws Exception {
        when(systemUserService.get(MIRO.getUuid())).thenReturn(MIRO);
        mockMvc.perform(get("/api/v1/users/".concat(MIRO.getUuid()))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(MIRO), false));
    }

    @Test
    public void updateSystemUser() throws Exception {
        SystemUser updatedUser = MIRO.copy().withUsername("mirom");
        when(systemUserService.update(updatedUser)).thenReturn(updatedUser);
        mockMvc.perform(put("/api/v1/users/".concat(updatedUser.getUuid()))
                                .content(mapper.writeValueAsString(updatedUser))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(updatedUser), false));
    }

    @Test
    public void deleteSystemUser() throws Exception {
        when(systemUserService.delete(MIRO.getUuid())).thenReturn(MIRO);
        mockMvc.perform(delete("/api/v1/users/".concat(MIRO.getUuid()))
                                .content(mapper.writeValueAsString(MIRO))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(MIRO), false));
    }


}

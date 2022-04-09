package model;

import com.example.userGuide.Constroller.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.*;
import com.example.userGuide.model.*;
import org.mockito.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
//@WithMockUser (not working, going to try without this)
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController usercontroller;

    User user = new User("password", "test123");
    user.setId(6942069L);

//    String exampleCourseJson =
//            "{\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn
//    Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";
// Do we need this Json stuff either???
    String exampleUserJson = "{\"id\":\"6942069\",\"password\":\"password\",\"username\":\"test123\"}";

    @Test
    public void retrieveUser() throws Exception {

        Mockito.when(usercontroller.UserById(6942069L)).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/user/6942069").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:6942069,password:password,username:test123}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

    }


}

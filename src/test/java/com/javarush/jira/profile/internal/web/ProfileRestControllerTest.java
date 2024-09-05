package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.login.internal.web.UserTestData;
import com.javarush.jira.profile.ProfileTo;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javarush.jira.profile.internal.web.ProfileTestData.PROFILE_TO_MATCHER;
import static com.javarush.jira.profile.internal.web.ProfileTestData.USER_PROFILE_TO;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestController.REST_URL;

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PROFILE_TO_MATCHER.contentJson(USER_PROFILE_TO));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void update() throws Exception {
        ProfileTo profileToAfterUpdate = ProfileTestData.getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(profileToAfterUpdate)))
                .andExpect(status().isNoContent());
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PROFILE_TO_MATCHER.contentJson(profileToAfterUpdate));
    }

    @Test
    void updateUnauthorized() throws Exception {
        ProfileTo profileToAfterUpdate = ProfileTestData.getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(profileToAfterUpdate)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void updateWithInvalidId() throws Exception {
        ProfileTo profileToWithInvalidId = ProfileTestData.getInvalidTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(profileToWithInvalidId)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void updateWithUnknownNotificationTo() throws Exception {
        ProfileTo profileToWithUnknownNotification = ProfileTestData.getWithUnknownNotificationTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(profileToWithUnknownNotification)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void updateWithUnknownContactTo() throws Exception {
        ProfileTo profileToWithUnknownContact = ProfileTestData.getWithUnknownContactTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(profileToWithUnknownContact)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = UserTestData.USER_MAIL)
    void updateWithContactHtmlUnsafeTo() throws Exception {
        ProfileTo profileToWithContactHtmlUnsafe = ProfileTestData.getWithContactHtmlUnsafeTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(profileToWithContactHtmlUnsafe)))
                .andExpect(status().isUnprocessableEntity());
    }

}
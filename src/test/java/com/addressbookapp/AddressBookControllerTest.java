package com.addressbookapp;

import com.addressbookapp.controller.AddressBookController;
import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(AddressBookController.class)
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenContact_whenAdded_shouldReturnSuccessMessage() throws Exception {

        ContactPerson contact = new ContactPerson(
                "Krishna", "Lal",
                "123 Street", "Bhopal",
                "MP", "462001",
                "9876543210", "krishna@gmail.com"
        );

        mockMvc.perform(post("/addressbook/add")
                .contentType("application/json")
                .content(mapper.writeValueAsString(contact)))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact Added Successfully"));
    }

    @Test
    public void givenContacts_whenFetched_shouldReturnContactList() throws Exception {

        ContactPerson contact = new ContactPerson(
                "Krishna", "Lal", "", "", "", "", "", ""
        );

        when(service.getAllContacts()).thenReturn(List.of(contact));

        mockMvc.perform(get("/addressbook/contacts"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenExistingContact_whenEdited_shouldReturnSuccessMessage() throws Exception {

        ContactPerson updated = new ContactPerson(
                "Krishna", "Lal",
                "New Address", "Delhi",
                "Delhi", "110001",
                "9999999999", "new@mail.com"
        );

        when(service.editContact("Krishna", "Lal", updated)).thenReturn(true);

        mockMvc.perform(put("/addressbook/edit?firstName=Krishna&lastName=Lal")
                .contentType("application/json")
                .content(mapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact Updated Successfully"));
    }
}
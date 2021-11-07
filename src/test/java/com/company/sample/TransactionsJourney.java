package com.company.sample;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.company.sample.support.model.ServiceResponse;
import com.company.sample.support.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 9090)
public class TransactionsJourney {

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper mapper = new ObjectMapper();


  @Test
  public void executeAndReturnTransactions() throws Exception {

    Transaction trxOne = new Transaction();
    trxOne.setSender("JohnDoe");
    trxOne.setReceiver("Max");
    trxOne.setAmount(10);

    Transaction trxTwo = new Transaction();
    trxTwo.setSender("JohnDoe");
    trxTwo.setReceiver("Max");
    trxTwo.setAmount(20);

    Transaction trxThree = new Transaction();
    trxThree.setSender("Max");
    trxThree.setReceiver("JohnDoe");
    trxThree.setAmount(30);

    UUID idOne = UUID.fromString("1195848e-f364-4155-87b4-08e593ea2a94");
    UUID idTwo = UUID.fromString("7a170ca5-c320-4467-9085-701a26e71039");
    UUID idThree = UUID.fromString("190dff96-08e5-4f6a-a26d-6d95529e1cdc");

    String responseOneStr = mapper.writeValueAsString(new ServiceResponse(idOne));
    String responseTwoStr = mapper.writeValueAsString(new ServiceResponse(idTwo));
    String responseThreeStr = mapper.writeValueAsString(new ServiceResponse(idThree));

    stubTransactionPerValue(responseOneStr, "10.0");

    stubTransactionPerValue(responseTwoStr, "20.0");

    stubTransactionPerValue(responseThreeStr, "30.0");

    executeTransaction(trxOne, idOne);

    executeTransaction(trxTwo, idTwo);

    executeTransaction(trxThree, idThree);

    mockMvc.perform(get("/api/transactions"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id", is(idOne.toString())))
        .andExpect(jsonPath("$[1].id", is(idTwo.toString())))
        .andExpect(jsonPath("$[2].id", is(idThree.toString())));

    mockMvc.perform(get("/api/transactions/JohnDoe"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(idOne.toString())))
        .andExpect(jsonPath("$[1].id", is(idTwo.toString())));

  }

  @Test
  void testException() throws Exception {
    mockMvc.perform(post("/api/transaction")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(new Transaction())))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", is("All the fields are mandatory.")));
  }

  private void stubTransactionPerValue(String responseOneStr, String value) {
    stubFor(WireMock.post(urlEqualTo("/executor/transaction"))
        .withRequestBody(WireMock.containing(value))
        .willReturn(
            aResponse().withHeader("Content-Type", "application/json").withBody(responseOneStr)));
  }

  private void executeTransaction(Transaction trxOne, UUID idOne) throws Exception {
    mockMvc.perform(post("/api/transaction")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(trxOne)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(idOne.toString())));
  }


}

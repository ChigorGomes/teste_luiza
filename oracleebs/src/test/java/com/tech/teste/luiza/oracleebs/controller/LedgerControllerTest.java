package com.tech.teste.luiza.oracleebs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.teste.luiza.oracleebs.config.filter.RequestLoggingFilter;
import com.tech.teste.luiza.oracleebs.dto.request.JournalEntriesRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.request.LedgerRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.request.LinesRequestRecord;
import com.tech.teste.luiza.oracleebs.dto.response.JournalEntriesResponseRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LedgerResponseRecord;
import com.tech.teste.luiza.oracleebs.dto.response.LinesResponseRecord;
import com.tech.teste.luiza.oracleebs.exception.NotFoundExceptionHandler;
import com.tech.teste.luiza.oracleebs.service.LedgerServiceImpl;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LedgerController.class)
@AutoConfigureMockMvc

@DisplayName("Testes para AuthenticationController")
@Import(TestSecurityConfig.class) //ignora a autenticação do spring security
class LedgerControllerTest {

    @InjectMocks
    @Autowired
    private LedgerController ledgerController;


    @Autowired
    private WebApplicationContext webApplicationContext;


    @MockitoBean
    private RequestLoggingFilter requestLoggingFilter;

    @MockitoBean
    private LedgerServiceImpl ledgerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }


    @Test
    @DisplayName("Deve retornar 201 quando um ledger for criado com sucesso")
    void shouldReturn201WhenLedgerIsCreatedSuccessfully() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "Ajuste de Despesas 2025",
                LocalDate.now(),
                "BRL",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "Ajuste de Despesas",
                journalEntriesRequestRecord
        );

        LinesResponseRecord linesResponseRecord = new LinesResponseRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesResponseRecord journalEntriesResponseRecord = new JournalEntriesResponseRecord(
                "Ajuste de Despesas 2025",
                LocalDate.now(),
                "BRL",
                Set.of(linesResponseRecord)
        );

        LedgerResponseRecord ledgerResponseRecord = new LedgerResponseRecord(
                UUID.randomUUID(),
                "Ajuste de Despesas",
                journalEntriesResponseRecord
        );
        when(ledgerService.create(ledgerRequestRecord)).thenReturn(ledgerResponseRecord);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Ensure response content type
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transcationId").value(ledgerResponseRecord.transcationId().toString()))
                .andExpect(jsonPath("$.description").value("Ajuste de Despesas"))
                .andExpect(jsonPath("$.journalEntries.currencyCode").value("BRL"))
                .andExpect(jsonPath("$.journalEntries.lines[0].costCenter").value("Centro de Custo"))
                .andExpect(jsonPath("$.journalEntries.lines[0].debit").value(20.0))
                .andExpect(jsonPath("$.journalEntries.lines[0].credit").value(10.0));

    }


    @Test
    @DisplayName("Deve retornar 200 e o ledger ao buscar com ID existente")
    void shouldReturn200AndLedgerWhenIdExists() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "Ajuste de Despesas 2025",
                LocalDate.now(),
                "BRL",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "Ajuste de Despesas",
                journalEntriesRequestRecord
        );

        LinesResponseRecord linesResponseRecord = new LinesResponseRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesResponseRecord journalEntriesResponseRecord = new JournalEntriesResponseRecord(
                "Ajuste de Despesas 2025",
                LocalDate.now(),
                "BRL",
                Set.of(linesResponseRecord)
        );

        LedgerResponseRecord ledgerResponseRecord = new LedgerResponseRecord(
                UUID.randomUUID(),
                "Ajuste de Despesas",
                journalEntriesResponseRecord
        );
        when(ledgerService.create(ledgerRequestRecord)).thenReturn(ledgerResponseRecord);
        when(ledgerService.findById(ledgerResponseRecord.transcationId())).thenReturn(ledgerResponseRecord);


        mockMvc.perform(get("/v1/ledger/{id}", ledgerResponseRecord.transcationId())  // Passando o ID do ledger na URL
                        .contentType(MediaType.APPLICATION_JSON))  // Tipo de conteúdo esperado
                .andExpect(status().isOk())  // Espera o status 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Espera o tipo de conteúdo JSON
                .andExpect(jsonPath("$.description").value("Ajuste de Despesas"))  // Verificando o campo 'description'
                .andExpect(jsonPath("$.journalEntries.journalName").value("Ajuste de Despesas 2025"));

    }

    @Test
    @DisplayName("Deve retornar 404 quando o ledger não for encontrado")
    void shouldReturn404WhenLedgerNotFound() throws Exception {
        UUID nonExistentId = UUID.randomUUID();
        when(ledgerService.findById(nonExistentId)).thenThrow(new NotFoundExceptionHandler(nonExistentId.toString()));
        mockMvc.perform(get("/v1/ledger/{id}", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value(nonExistentId + " not found"));
    }

    @Test
    @DisplayName("Deve retornar 404 quando o campo transactionId não for passado")
    void shouldReturn404WhenLedgerEmpty() throws Exception {
        mockMvc.perform(get("/v1/ledger/{id}", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("No static resource v1/ledger."));
    }

    @Test
    @DisplayName("Deve retornar 405 quando o campo transactionId não for passado e nem a '/'")
    void shouldReturn405WhenLedgerEmpty() throws Exception {
        mockMvc.perform(get("/v1/ledger", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Request method 'GET' is not supported"));
    }

    @Test
    @DisplayName("Deve retornar 500 quando o campo transactionId for do tipo inválido")
    void shouldReturn500WhenLedgerInvalid() throws Exception {
        mockMvc.perform(get("/v1/ledger/{id}", "teste")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", containsString("Failed to convert value of type 'java.lang.String'")));
    }


    @Test
    @DisplayName("Deve retornar 400 quando a descrição do ledger estiver vazia")
    void shouldReturn400WhenLedgerDescriptionIsEmpty() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "Ajuste de Despesas 2025",
                LocalDate.now(),
                "BRL",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields").value("description cannot be null or empty"));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o campo journalName do journalEntries estiver vazia")
    void shouldReturn400WhenJournalEntriesjournalNameIsEmpty() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "",
                LocalDate.now(),
                "BRL",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields").value("journalName cannot be null or empty"));
    }


    @Test
    @DisplayName("Deve retornar 400 quando o campo currencyCode do journalEntries estiver vazia")
    void shouldReturn400WhenJournalEntriesCurrencyCodeIsEmpty() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("currencyCode cannot be null or empty")))
                .andExpect(jsonPath("$.fields", hasItem("CurrencyCode is not valid")));
    }


    @Test
    @DisplayName("Deve retornar 400 quando o campo currencyCode do journalEntries for inválido")
    void shouldReturn400WhenJournalEntriesCurrencyCodeIsInvalid() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "abc",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("CurrencyCode is not valid")));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o campo accountCode do Lines estiver vazia")
    void shouldReturn400WhenLinesAccountCodeIsEmpty() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "",
                20.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "USD",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("accountCode cannot be null or empty")));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o campo debit do Lines for inválido com mais de 2 casas decimais")
    void shouldReturn400WhenLinesDebitIsInvalid() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                15.1456,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "USD",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("The number must have at most two decimal places.")));
    }


    @Test
    @DisplayName("Deve retornar 400 quando o campo debit do Lines for menor que zero")
    void shouldReturn400WhenLinesDebitIsLessThanZero() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                -10.00,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "USD",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("debit must be greater than zero.")));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o campo debit do Lines for igual a zero")
    void shouldReturn400WhenLinesDebitIsEqualsZero() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                0.0,
                10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "USD",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("debit must be greater than zero.")));
    }


    @Test
    @DisplayName("Deve retornar 400 quando o campo credit do Lines for inválido com mais de 2 casas decimais")
    void shouldReturn400WhenLinesCreditIsInvalid() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                15.14,
                10.456,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "USD",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("The number must have at most two decimal places.")));
    }


    @Test
    @DisplayName("Deve retornar 400 quando o campo credit do Lines for menor que zero")
    void shouldReturn400WhenLinesCreditIsLessThanZero() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                10.00,
                -10.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "USD",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("credit must be greater than zero.")));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o campo credit do Lines for igual a zero")
    void shouldReturn400WhenLinesCreditIsEqualsZero() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                10.0,
                0.0,
                "Centro de Custo",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "gastos",
                LocalDate.now(),
                "USD",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields", hasItem("credit must be greater than zero.")));
    }


    @Test
    @DisplayName("Deve retornar 400 quando o campo costCenter do lines estiver vazio")
    void shouldReturn400WhenLinesCostCenterIsEmpty() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "",
                "Despesa com material de escritório"
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "testes",
                LocalDate.now(),
                "BRL",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields").value("costCenter cannot be null or empty"));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o campo description do lines estiver vazio")
    void shouldReturn400WhenLinesDescriptionIsEmpty() throws Exception {
        LinesRequestRecord linesRequestRecord = new LinesRequestRecord(
                "CC1001",
                20.0,
                10.0,
                "CB01",
                ""
        );

        JournalEntriesRequestRecord journalEntriesRequestRecord = new JournalEntriesRequestRecord(
                "testes",
                LocalDate.now(),
                "BRL",
                Set.of(linesRequestRecord)
        );

        LedgerRequestRecord ledgerRequestRecord = new LedgerRequestRecord(
                "gastos",
                journalEntriesRequestRecord
        );

        when(ledgerService.create(ledgerRequestRecord)).thenThrow(ValidationException.class);

        mockMvc.perform(post("/v1/ledger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ledgerRequestRecord)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields").value("description cannot be null or empty"));
    }

}
package com.proyect.bankaccount.infraestructure.controllers.client;

import com.proyect.bankaccount.application.mapper.client.IClientMapperRequestDomain;
import com.proyect.bankaccount.application.mapper.client.IClientMapperResponseDomain;
import com.proyect.bankaccount.application.service.ClientService;
import com.proyect.bankaccount.domain.model.client.Client;
import com.proyect.bankaccount.infraestructure.controllers.client.request.ClientRequest;
import com.proyect.bankaccount.infraestructure.controllers.client.response.ClientAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;
    private final IClientMapperRequestDomain clientMapperRequestDomain;

    private final IClientMapperResponseDomain clientMapperResponseDomain;

    @Autowired
    public ClientController(ClientService clientService, IClientMapperRequestDomain clientMapperRequestDomain, IClientMapperResponseDomain clientMapperResponseDomain) {
        this.clientService = clientService;
        this.clientMapperRequestDomain = clientMapperRequestDomain;
        this.clientMapperResponseDomain = clientMapperResponseDomain;
    }


    @PostMapping(value="/create-client", headers = "Accept=application/json")
    public ResponseEntity<ClientRequest> save(@RequestBody ClientRequest ClientRequest) {
        Client client = clientMapperRequestDomain.toClient(ClientRequest);
        Client createdClient = clientService.createClient(client);
        ClientRequest response = clientMapperRequestDomain.toClientRequest(createdClient);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/list", headers = "Accept=application/json")
    public ResponseEntity<List<ClientAccountResponse>> getAll(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String identificationNumber
    ) {
        List<ClientAccountResponse> responseList;

        if (id != null) {
            Optional<Client> client = clientService.getClientById(id);
            responseList = client
                    .map(a -> Collections.singletonList(clientMapperResponseDomain.toClientAccountResponse(a)))
                    .orElse(Collections.emptyList());
        } else if (identificationNumber != null) {
            Optional<Client> clientListNumber = clientService.findByIdentificationNumber(identificationNumber);
            responseList = clientListNumber
                    .map(a -> Collections.singletonList(clientMapperResponseDomain.toClientAccountResponse(a)))
                    .orElse(Collections.emptyList());
        }else {
            List<Client> clientListAll = clientService.getAllClient();
            responseList = clientMapperResponseDomain.toClientResponseList(clientListAll);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}

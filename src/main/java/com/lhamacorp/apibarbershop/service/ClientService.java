package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.Client;
import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientDTO;
import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientUpdateDTO;
import com.lhamacorp.apibarbershop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class ClientService {

    @Autowired

    private ClientRepository clientRepository;

    public URI registerClient(ClientRegisterDTO clientRegisterData, UriComponentsBuilder uriBuilder){

        Client client = new Client(clientRegisterData);
        clientRepository.save(client);
        URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getIdClient()).toUri();

        return uri;
    }

    public List<ClientDTO> getAllClients(){

        List<ClientDTO> list = clientRepository.findAllByActiveTrue();
        return list;
    }

    public void deleteClient(Long idClient){

        Client client = clientRepository.getReferenceById(idClient);
        client.setActive(false);
    }

    public ClientUpdateDTO updateClientById(ClientUpdateDTO clientData){

        Client client = clientRepository.getReferenceById(clientData.idClient());

        if(clientData.name() != null){
            client.setName(clientData.name());
        }
        if(clientData.phone() != null){
            client.setPhone(clientData.phone());
        }

        return new ClientUpdateDTO(client);
    }


}

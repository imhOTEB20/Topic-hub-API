package com.aluralatam.topichubapirest.controller;

import com.aluralatam.topichubapirest.domain.server.*;
import com.aluralatam.topichubapirest.domain.topic.DTOGetTopic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/servers")
public class ServerController {

    @Autowired
    private ServerRepository serverRepository;

    @PostMapping
    public ResponseEntity<DTOResponseServer> registerServer(@RequestBody @Valid DTORegisterServer dtoRegisterServer,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        var server = serverRepository.save(new Server(dtoRegisterServer));
        URI url = uriComponentsBuilder.path("/servers/{id}").buildAndExpand(server.getId()).toUri();
        return ResponseEntity.created(url).body(new DTOResponseServer(server));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DTOGetServer> getServer(@PathVariable Long id) {
        return ResponseEntity.ok(new DTOGetServer(serverRepository.getReferenceById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<DTOGetServer>> allServers(@PageableDefault(sort = "name")Pageable pagination) {
        return ResponseEntity.ok(serverRepository.findAll(pagination)
                .map(DTOGetServer::new));
    }

    @PutMapping
    public ResponseEntity<DTOResponseServer> updateServer(@RequestBody DTOUpdateServer dtoUpdateServer) {
        var server = serverRepository.getReferenceById(dtoUpdateServer.id());
        server.updateData(dtoUpdateServer);
        return ResponseEntity.noContent().build();
    }
}

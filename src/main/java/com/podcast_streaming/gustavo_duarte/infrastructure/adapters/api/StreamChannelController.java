package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.podcast_streaming.gustavo_duarte.application.queries.stream_channels.ListStreamChannelsService;
import com.podcast_streaming.gustavo_duarte.application.services.stream_channels.CreateStreamChannelsService;
import com.podcast_streaming.gustavo_duarte.application.services.stream_channels.DeleteStreamChannelsService;
import com.podcast_streaming.gustavo_duarte.application.services.stream_channels.UpdateStreamChannelsService;
import com.podcast_streaming.gustavo_duarte.model.domain.StreamChannel;
import com.podcast_streaming.gustavo_duarte.model.presenter.StreamChannelPresenter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/stream-channels")
public class StreamChannelController {
  private ListStreamChannelsService listStreamChannelsService;
  private CreateStreamChannelsService createStreamChannelsService;
  private UpdateStreamChannelsService updateStreamChannelsService;
  private DeleteStreamChannelsService deleteStreamChannelsService;
  
  @Autowired
  public StreamChannelController(
    ListStreamChannelsService listStreamChannelsService,
    CreateStreamChannelsService createStreamChannelsService,
    UpdateStreamChannelsService updateStreamChannelsService,
    DeleteStreamChannelsService deleteStreamChannelsService
  ) {
    this.listStreamChannelsService = listStreamChannelsService;
    this.createStreamChannelsService = createStreamChannelsService;
    this.updateStreamChannelsService = updateStreamChannelsService;
    this.deleteStreamChannelsService = deleteStreamChannelsService;
  }

  @GetMapping()
  @Operation(summary = "Lista todos os canais de stream")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de canais de stream")
  })
  public Iterable<StreamChannel> listStreamChannels() {
    return listStreamChannelsService.listStreamChannels();
  }

  @PostMapping()
  @Operation(summary = "Cria um novo canal de stream")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Canal de stream criado")
  })
  public StreamChannel createStreamChannel(@RequestBody StreamChannelPresenter streamChannelPresenter) {
    return createStreamChannelsService.create(streamChannelPresenter.toDomain());
  }
  
  @PostMapping("/{uuid}")
  @Operation(summary = "Atualiza um canal de stream")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Canal de stream atualizado")
  })
  public StreamChannel updateStreamChannel(@PathVariable String uuid, @RequestBody StreamChannelPresenter streamChannelPresenter) {
    streamChannelPresenter.setUuid(uuid);
    return updateStreamChannelsService.update(streamChannelPresenter.toDomain());
  }

  @DeleteMapping("/{uuid}")
  @Operation(summary = "Deleta um canal de stream")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Canal de stream deletado")
  })
  public void deleteStreamChannel(@PathVariable String uuid) {
    StreamChannelPresenter streamChannelPresenter = new StreamChannelPresenter();
    streamChannelPresenter.setUuid(uuid);
    deleteStreamChannelsService.delete(streamChannelPresenter.toDomain());
  }
}

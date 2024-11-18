package com.podcast_streaming.gustavo_duarte.infrastructure.adapters.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.podcast_streaming.gustavo_duarte.application.queries.albums.FindAlbumService;
import com.podcast_streaming.gustavo_duarte.application.queries.albums.ListAlbumsService;
import com.podcast_streaming.gustavo_duarte.application.services.albums.CreateAlbumService;
import com.podcast_streaming.gustavo_duarte.application.services.albums.DeleteAlbumService;
import com.podcast_streaming.gustavo_duarte.model.domain.Album;
import com.podcast_streaming.gustavo_duarte.model.presenter.AlbumPresenter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/stream-channels/{streamChannelUuid}/albums")
public class AlbumController {

  private ListAlbumsService listAlbumsService;
  private FindAlbumService findAlbumService;
  private CreateAlbumService createAlbumService;
  private DeleteAlbumService deleteAlbumService;
  
  @Autowired
  public AlbumController(
    ListAlbumsService listAlbumsService,
    CreateAlbumService createAlbumService,
    FindAlbumService findAlbumService,
    DeleteAlbumService deleteAlbumService
  ) {
    this.listAlbumsService = listAlbumsService;
    this.createAlbumService = createAlbumService;
    this.findAlbumService = findAlbumService;
    this.deleteAlbumService = deleteAlbumService;
  }

  @GetMapping()
  @Operation(summary = "Lista todos os albums")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de albums")
  })
  public List<Album> listAlbums(
    @RequestParam(defaultValue = "0") String page,
    @PathVariable String streamChannelUuid
  ){
    return listAlbumsService.listAlbums(page, streamChannelUuid);
  }

  @GetMapping("/{albumsUuid}")
  @Operation(summary = "Busca um album")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Album encontrado")
  })
  public Album findAlbum(@PathVariable String albumsUuid){
    return findAlbumService.findAlbum(albumsUuid);
  }

  @PostMapping()
  @Operation(summary = "Cria um novo album")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Album criado")
  })
  public Album createAlbum(
  @RequestPart("files") MultipartFile[] files,
  @RequestPart("album") AlbumPresenter albumPresenter,
  @PathVariable String streamChannelUuid) {
    albumPresenter.setStreamChannelUuid(streamChannelUuid);
    return createAlbumService.create(albumPresenter.toDomain(), files);
  }
  
  @DeleteMapping("/{albumsUuid}")
  @Operation(summary = "Deleta um album")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Album deletado")
  })
  public void deleteAlbum(@PathVariable String streamChannelUuid, @PathVariable String albumsUuid){
    deleteAlbumService.delete(streamChannelUuid, albumsUuid);
  }
}

package  ma.sir.easystock.ws.facade.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.sir.easystock.bean.core.Client;
import ma.sir.easystock.bean.history.ClientHistory;
import ma.sir.easystock.dao.criteria.core.ClientCriteria;
import ma.sir.easystock.dao.criteria.history.ClientHistoryCriteria;
import ma.sir.easystock.service.facade.admin.ClientAdminService;
import ma.sir.easystock.ws.converter.ClientConverter;
import ma.sir.easystock.ws.dto.ClientDto;
import ma.sir.easystock.zynerator.controller.AbstractController;
import ma.sir.easystock.zynerator.dto.AuditEntityDto;
import ma.sir.easystock.zynerator.dto.FileTempDto;
import ma.sir.easystock.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.sir.easystock.zynerator.process.Result;
import org.springframework.web.multipart.MultipartFile;

import static java.nio.file.Paths.get;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@Api("Manages client services")
@RestController
@RequestMapping("/api/admin/client/")
public class ClientRestAdmin  extends AbstractController<Client, ClientDto, ClientHistory, ClientCriteria, ClientHistoryCriteria, ClientAdminService, ClientConverter> {

    public static final String DIRECTORY = System.getProperty("D:/upload/");

    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }

    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestParam("files") MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @ApiOperation("download file")
    @GetMapping("downloadFile/{id}")
    public ResponseEntity<InputStreamResource> downloadCr(@PathVariable Long id,String[] includes, String[] excludes) throws Exception {
        Client client = service.findById(id);
        if(client != null && client.getFile() != null)
            return getFileResource(client.getFile().getFilePath());
        return new ResponseEntity("", HttpStatus.CONFLICT);
    }


    @ApiOperation("Finds a list of all clients")
    @GetMapping("")
    public ResponseEntity<List<ClientDto>> findAll() throws Exception {
        return super.findAll();
    }
    @ApiOperation("Updates the specified  client")
    @PutMapping("")
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto dto) throws Exception {
        return super.update(dto);
    }
    @ApiOperation("Finds a client by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }
    @ApiOperation("Saves the specified  client")
    @PostMapping("")
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto dto) throws Exception {
        return super.save(dto);
    }
    @ApiOperation("Delete list of client")
    @PostMapping("multiple")
    public ResponseEntity<List<ClientDto>> delete(@RequestBody List<ClientDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @ApiOperation("Delete the specified client")
    @DeleteMapping("")
    public ResponseEntity<ClientDto> delete(@RequestBody ClientDto dto) throws Exception {
            return super.delete(dto);
    }

    @ApiOperation("Delete the specified client")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @ApiOperation("Delete multiple clients by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }
    @ApiOperation("Finds clients by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ClientDto>> findByCriteria(@RequestBody ClientCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @ApiOperation("Finds paginated clients by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ClientCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @ApiOperation("Exports clients by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody ClientCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @ApiOperation("Gets client data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ClientCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }

    @ApiOperation("Gets client history by id")
    @GetMapping("history/id/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @ApiOperation("Gets client paginated history by criteria")
    @PostMapping("history-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody ClientHistoryCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @ApiOperation("Exports client history by criteria")
    @PostMapping("export-history")
    public ResponseEntity<InputStreamResource> exportHistory(@RequestBody ClientHistoryCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @ApiOperation("Gets client history data size by criteria")
    @PostMapping("history-data-size")
    public ResponseEntity<Integer> getHistoryDataSize(@RequestBody ClientHistoryCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }
    public ClientRestAdmin (ClientAdminService service, ClientConverter converter) {
        super(service, converter);
    }


}
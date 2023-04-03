package ma.sir.easystock.ws.facade.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.sir.easystock.bean.core.CategorieProduit;
import ma.sir.easystock.bean.history.CategorieProduitHistory;
import ma.sir.easystock.dao.criteria.core.CategorieProduitCriteria;
import ma.sir.easystock.dao.criteria.history.CategorieProduitHistoryCriteria;
import ma.sir.easystock.service.facade.admin.CategorieProduitAdminService;
import ma.sir.easystock.ws.converter.CategorieProduitConverter;
import ma.sir.easystock.ws.dto.CategorieProduitDto;
import ma.sir.easystock.zynerator.controller.AbstractController;
import ma.sir.easystock.zynerator.dto.AuditEntityDto;
import ma.sir.easystock.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages CategorieProduit services")
@RestController
@RequestMapping("/api/admin/categorieProduit/")
public class CategorieProduitRestAdmin extends AbstractController<CategorieProduit, CategorieProduitDto, CategorieProduitHistory, CategorieProduitCriteria, CategorieProduitHistoryCriteria, CategorieProduitAdminService, CategorieProduitConverter> {

    @ApiOperation("Finds a list of all CategorieProduits")
    @GetMapping("")
    public ResponseEntity<List<CategorieProduitDto>> findAll() throws Exception {
        return super.findAll();
    }
    @ApiOperation("Updates the specified  CategorieProduit")
    @PutMapping("")
    public ResponseEntity<CategorieProduitDto> update(@RequestBody CategorieProduitDto dto) throws Exception {
        return super.update(dto);
    }
    @ApiOperation("Finds a CategorieProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategorieProduitDto> findById(@PathVariable Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }
    @ApiOperation("Saves the specified  CategorieProduit")
    @PostMapping("")
    public ResponseEntity<CategorieProduitDto> save(@RequestBody CategorieProduitDto dto) throws Exception {
        return super.save(dto);
    }
    @ApiOperation("Delete list of CategorieProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<CategorieProduitDto>> delete(@RequestBody List<CategorieProduitDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @ApiOperation("Delete the specified CategorieProduit")
    @DeleteMapping("")
    public ResponseEntity<CategorieProduitDto> delete(@RequestBody CategorieProduitDto dto) throws Exception {
            return super.delete(dto);
    }

    @ApiOperation("Delete the specified CategorieProduit")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @ApiOperation("Delete multiple CategorieProduits by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }
    @ApiOperation("Finds CategorieProduits by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategorieProduitDto>> findByCriteria(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @ApiOperation("Finds paginated CategorieProduits by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @ApiOperation("Exports CategorieProduits by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @ApiOperation("Gets CategorieProduit data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategorieProduitCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }

    @ApiOperation("Gets CategorieProduit history by id")
    @GetMapping("history/id/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @ApiOperation("Gets CategorieProduit paginated history by criteria")
    @PostMapping("history-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody CategorieProduitHistoryCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @ApiOperation("Exports CategorieProduit history by criteria")
    @PostMapping("export-history")
    public ResponseEntity<InputStreamResource> exportHistory(@RequestBody CategorieProduitHistoryCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @ApiOperation("Gets CategorieProduit history data size by criteria")
    @PostMapping("history-data-size")
    public ResponseEntity<Integer> getHistoryDataSize(@RequestBody CategorieProduitHistoryCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }
    public CategorieProduitRestAdmin(CategorieProduitAdminService service, CategorieProduitConverter converter) {
        super(service, converter);
    }


}
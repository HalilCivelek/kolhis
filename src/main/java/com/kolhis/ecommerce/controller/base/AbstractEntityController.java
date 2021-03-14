package com.kolhis.ecommerce.controller.base;

import com.kolhis.ecommerce.entity.BaseEntity;
import com.kolhis.ecommerce.entity.dto.UsersDto;
import com.kolhis.ecommerce.repository.BaseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@Component
public abstract class AbstractEntityController<DTO extends BaseDto, Entity extends BaseEntity, PK extends Serializable> extends AbstractReadEntityController<DTO, Entity, PK> {
    public AbstractEntityController() {
    }

    // @LogDatabase
    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") PK id) {
        this.getService().delete(id);
    }

    //@LogDatabase
    @PostMapping
    public void save(@RequestBody @Valid DTO dto) {
        this.getService().save(toEntity(dto));
    }

    // @LogDatabase
    @PutMapping({"/{id}"})
    public void put(@PathVariable("id") PK id, @RequestBody DTO dto) {
        Entity forSave = toEntity(dto);
        this.getService().put(id, forSave);
    }

    // @ExcludeLog
  /*  @PostMapping("/multi-update/export")
    public ResponseEntity<InputStreamResource> getEditableRecordsAsExcel(@RequestBody MultiEditExportDto multiEditExportDto) {
        ByteArrayInputStream in = getService().getEditableRecordsAsExcel(multiEditExportDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=multi-edit-export.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @ExcludeLog
    @PostMapping(value = "/multi-update/import")
    public void importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        getService().importExcelForUpdate(worksheet);
    }*/
}


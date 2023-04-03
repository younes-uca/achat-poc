package ma.sir.easystock.zynerator.controller;

import ma.sir.easystock.zynerator.audit.AuditBusinessObjectEnhanced;
import ma.sir.easystock.zynerator.converter.AbstractReferentielConverter;
import ma.sir.easystock.zynerator.criteria.BaseCriteria;
import ma.sir.easystock.zynerator.dto.ReferentielBaseDto;
import ma.sir.easystock.zynerator.history.HistBusinessObject;
import ma.sir.easystock.zynerator.service.IService;

public class AbstractReferentielController<T extends AuditBusinessObjectEnhanced, DTO extends ReferentielBaseDto, H extends HistBusinessObject, Criteria extends BaseCriteria, HistoryCriteria extends BaseCriteria, SERV extends IService<T, Criteria, HistoryCriteria>, CONV extends AbstractReferentielConverter<T, DTO, H>> extends AbstractController<T, DTO, H, Criteria, HistoryCriteria, SERV, CONV> {


    public AbstractReferentielController(SERV service, CONV converter) {
        super(service, converter);
    }

}

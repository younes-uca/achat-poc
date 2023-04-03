package ma.sir.easystock.service.impl.admin;

import ma.sir.easystock.bean.core.CategorieProduit;
import ma.sir.easystock.bean.core.Produit;
import ma.sir.easystock.bean.history.CategorieProduitHistory;
import ma.sir.easystock.dao.criteria.core.CategorieProduitCriteria;
import ma.sir.easystock.dao.criteria.history.CategorieProduitHistoryCriteria;
import ma.sir.easystock.dao.facade.core.CategorieProduitDao;
import ma.sir.easystock.dao.facade.history.CategorieProduitHistoryDao;
import ma.sir.easystock.dao.specification.core.CategorieProduitSpecification;
import ma.sir.easystock.service.facade.admin.CategorieProduitAdminService;
import ma.sir.easystock.zynerator.service.AbstractServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategorieProduitAdminServiceImpl extends AbstractServiceImpl<CategorieProduit, CategorieProduitHistory, CategorieProduitCriteria, CategorieProduitHistoryCriteria, CategorieProduitDao,
        CategorieProduitHistoryDao> implements CategorieProduitAdminService {


    public Produit findByReferenceEntity(Produit t) {
        return dao.findByReference(t.getReference());
    }


    public void configure() {
        super.configure(CategorieProduit.class, CategorieProduitHistory.class, CategorieProduitHistoryCriteria.class, CategorieProduitSpecification.class);
    }

    public CategorieProduitAdminServiceImpl(CategorieProduitDao dao, CategorieProduitHistoryDao historyDao) {
        super(dao, historyDao);
    }

}
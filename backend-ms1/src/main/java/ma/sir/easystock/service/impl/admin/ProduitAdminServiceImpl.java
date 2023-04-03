package ma.sir.easystock.service.impl.admin;

import ma.sir.easystock.bean.core.Produit;
import ma.sir.easystock.bean.history.ProduitHistory;
import ma.sir.easystock.dao.criteria.core.ProduitCriteria;
import ma.sir.easystock.dao.criteria.history.ProduitHistoryCriteria;
import ma.sir.easystock.dao.facade.core.ProduitDao;
import ma.sir.easystock.dao.facade.history.ProduitHistoryDao;
import ma.sir.easystock.dao.specification.core.ProduitSpecification;
import ma.sir.easystock.service.facade.admin.ProduitAdminService;
import ma.sir.easystock.zynerator.service.AbstractServiceImpl;
import ma.sir.easystock.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;






import java.util.List;
@Service
public class ProduitAdminServiceImpl extends AbstractServiceImpl<Produit,ProduitHistory, ProduitCriteria, ProduitHistoryCriteria, ProduitDao,
ProduitHistoryDao> implements ProduitAdminService {


    public Produit findByReferenceEntity(Produit t){
        return  dao.findByReference(t.getReference());
    }


    public void configure() {
        super.configure(Produit.class,ProduitHistory.class, ProduitHistoryCriteria.class, ProduitSpecification.class);
    }

    public ProduitAdminServiceImpl(ProduitDao dao, ProduitHistoryDao historyDao) {
        super(dao, historyDao);
    }

}
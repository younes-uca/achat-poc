package ma.sir.easystock.service.impl.admin;

import ma.sir.easystock.bean.core.Achat;
import ma.sir.easystock.bean.core.AchatItem;
import ma.sir.easystock.bean.history.AchatHistory;
import ma.sir.easystock.dao.criteria.core.AchatCriteria;
import ma.sir.easystock.dao.criteria.history.AchatHistoryCriteria;
import ma.sir.easystock.dao.facade.core.AchatDao;
import ma.sir.easystock.dao.facade.history.AchatHistoryDao;
import ma.sir.easystock.dao.specification.core.AchatSpecification;
import ma.sir.easystock.service.facade.admin.AchatAdminService;
import ma.sir.easystock.service.facade.admin.AchatItemAdminService;
import ma.sir.easystock.service.facade.admin.ClientAdminService;
import ma.sir.easystock.zynerator.service.AbstractServiceImpl;
import ma.sir.easystock.zynerator.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AchatAdminServiceImpl extends AbstractServiceImpl<Achat, AchatHistory, AchatCriteria, AchatHistoryCriteria, AchatDao,
        AchatHistoryDao> implements AchatAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Achat create(Achat t) {
        prepareAchat(t);
        super.create(t);
        if (t.getAchatItems() != null) {
            t.getAchatItems().forEach(element -> {
                element.setAchat(t);
                achatItemService.create(element);
            });
        }
        return t;
    }

    private void prepareAchat(Achat t) {
        BigDecimal total = BigDecimal.ZERO;
        if (t.getAchatItems() != null) {
            for (AchatItem achatItem : t.getAchatItems()) {
                total = total.add(achatItem.getQuantite().multiply(achatItem.getPrixUnitaire()));
            }
        }
        t.setTotal(total);
        t.setDateAchat(LocalDateTime.now());
    }

    public Achat findWithAssociatedLists(Long id) {
        Achat result = dao.findById(id).orElse(null);
        if (result != null && result.getId() != null) {
            result.setAchatItems(achatItemService.findByAchatId(id));
        }
        return result;
    }

    @Transactional
    public void deleteAssociatedLists(Long id) {
        achatItemService.deleteByAchatId(id);
    }


    public void updateWithAssociatedLists(Achat achat) {
        if (achat != null && achat.getId() != null) {
            List<List<AchatItem>> resultAchatItems = achatItemService.getToBeSavedAndToBeDeleted(achatItemService.findByAchatId(achat.getId()), achat.getAchatItems());
            achatItemService.delete(resultAchatItems.get(1));
            ListUtil.emptyIfNull(resultAchatItems.get(0)).forEach(e -> e.setAchat(achat));
            achatItemService.update(resultAchatItems.get(0), true);
        }
    }

    public Achat findByReferenceEntity(Achat t) {
        return dao.findByReference(t.getReference());
    }

    public List<Achat> findByClientId(Long id) {
        return dao.findByClientId(id);
    }

    public int deleteByClientId(Long id) {
        return dao.deleteByClientId(id);
    }

    public void configure() {
        super.configure(Achat.class, AchatHistory.class, AchatHistoryCriteria.class, AchatSpecification.class);
    }

    @Autowired
    private AchatItemAdminService achatItemService;
    @Autowired
    private ClientAdminService clientService;

    public AchatAdminServiceImpl(AchatDao dao, AchatHistoryDao historyDao) {
        super(dao, historyDao);
    }

}
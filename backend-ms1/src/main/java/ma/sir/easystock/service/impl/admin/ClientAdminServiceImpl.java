package ma.sir.easystock.service.impl.admin;

import ma.sir.easystock.bean.core.Client;
import ma.sir.easystock.bean.history.ClientHistory;
import ma.sir.easystock.dao.criteria.core.ClientCriteria;
import ma.sir.easystock.dao.criteria.history.ClientHistoryCriteria;
import ma.sir.easystock.dao.facade.core.ClientDao;
import ma.sir.easystock.dao.facade.history.ClientHistoryDao;
import ma.sir.easystock.dao.specification.core.ClientSpecification;
import ma.sir.easystock.service.facade.admin.ClientAdminService;
import ma.sir.easystock.zynerator.service.AbstractServiceImpl;
import ma.sir.easystock.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;






import java.util.List;
@Service
public class ClientAdminServiceImpl extends AbstractServiceImpl<Client,ClientHistory, ClientCriteria, ClientHistoryCriteria, ClientDao,
ClientHistoryDao> implements ClientAdminService {


    public Client findByReferenceEntity(Client t){
        return  dao.findByCin(t.getCin());
    }


    public void configure() {
        super.configure(Client.class,ClientHistory.class, ClientHistoryCriteria.class, ClientSpecification.class);
    }

    public ClientAdminServiceImpl(ClientDao dao, ClientHistoryDao historyDao) {
        super(dao, historyDao);
    }

}
package ma.sir.easystock.service.facade.admin;

import java.util.List;
import ma.sir.easystock.bean.core.Achat;
import ma.sir.easystock.dao.criteria.core.AchatCriteria;
import ma.sir.easystock.dao.criteria.history.AchatHistoryCriteria;
import ma.sir.easystock.zynerator.service.IService;

public interface AchatAdminService extends  IService<Achat,AchatCriteria, AchatHistoryCriteria>  {

    List<Achat> findByClientId(Long id);
    int deleteByClientId(Long id);



}

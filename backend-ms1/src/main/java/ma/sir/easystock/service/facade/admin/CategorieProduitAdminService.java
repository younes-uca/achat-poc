package ma.sir.easystock.service.facade.admin;

import ma.sir.easystock.bean.core.CategorieProduit;
import ma.sir.easystock.bean.core.Produit;
import ma.sir.easystock.dao.criteria.core.CategorieProduitCriteria;
import ma.sir.easystock.dao.criteria.core.ProduitCriteria;
import ma.sir.easystock.dao.criteria.history.CategorieProduitHistoryCriteria;
import ma.sir.easystock.dao.criteria.history.ProduitHistoryCriteria;
import ma.sir.easystock.zynerator.service.IService;

public interface CategorieProduitAdminService extends  IService<CategorieProduit, CategorieProduitCriteria, CategorieProduitHistoryCriteria>  {




}
